package com.mefrreex.chestcreator.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryCloseEvent;
import cn.nukkit.inventory.Inventory;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;

/**
 * This class is the handler for closing the chest
 */
public class ChestListener implements Listener {
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = event.getInventory();
        
        PlayerChest playerChest = ChestManager.getPlayerChest().get(player);
        if (playerChest != null && matchInventories(inventory, playerChest.inventory())) {
                playerChest.chest().getCloseActions().forEach(action -> action.execute(player));
                ChestManager.getPlayerChest().remove(player);
        }
    }

    private boolean matchInventories(Inventory inventory, Inventory chestInventory) {
        return inventory.getTitle().equals(chestInventory.getTitle()) &&
               inventory.getType().equals(chestInventory.getType());
    }
}
