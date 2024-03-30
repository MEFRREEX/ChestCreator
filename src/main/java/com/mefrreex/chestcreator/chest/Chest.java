package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.inventory.fake.FakeInventoryType;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;
import com.mefrreex.chestcreator.chest.action.Action;
import com.mefrreex.chestcreator.chest.command.ChestCommand;
import com.mefrreex.chestcreator.chest.command.ChestCommandExecutor;
import com.mefrreex.chestcreator.chest.element.ItemElement;
import com.mefrreex.chestcreator.event.ChestSendEvent;
import com.mefrreex.chestcreator.utils.Format;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter 
@Setter
@ToString
public class Chest {
    
    private ChestCommand command;
    private transient ChestCommandExecutor executableCommand;

    private FakeInventoryType type;
    private ChestSwitchMode switchMode;
    private String title;
    private Map<Integer, ItemElement> items = new HashMap<>();

    private List<Action> openActions = new ArrayList<>();
    private List<Action> closeActions = new ArrayList<>();

    public Chest() {
        this(FakeInventoryType.CHEST);
    }

    public Chest(FakeInventoryType type) {
        this(type, "");
    }

    public Chest(FakeInventoryType type, String title) {
        this.title = title; 
    }

    /**
     * Initialize the chest
     */
    public void init() {
        if (this.command != null && command.isEnable()) {
            this.executableCommand = new ChestCommandExecutor(command, this);
            Server.getInstance().getCommandMap().register("ChestCreator", executableCommand);
        }
    }

    /**
     * Set chest item
     * @param item ItemElement
     */
    public Chest setItem(int slot, ItemElement item) {
        items.put(slot, item);
        return this;
    }

    /**
     * Add an action to open the chest
     */
    public Chest addOpenAction(Action action) {
        openActions.add(action);
        return this;
    }

    /**
     * Add an action to close the chest
     */
    public Chest addCloseAction(Action action) {
        closeActions.add(action);
        return this;
    }

    /**
     * Get chest switch mode
     * @return ChestSwitchMode
     */
    public ChestSwitchMode getSwitchMode() {
        return switchMode != null ? switchMode : ChestSwitchMode.DEFAULT;
    }

    /**
     * Create a chest in FakeInventories
     * @param player Player
     * @return Inventory
     */
    public ChestInventory build(Player player) {
        ChestInventory inventory = new ChestInventory(type, Format.format(title, player));
        inventory.setDefaultItemHandler((inv, slot, oldItem, newItem, event) -> event.setCancelled());
        inventory.setCloseHandler(pl -> {
            closeActions.forEach(action -> action.execute(player));
        });

        items.forEach((slot, item) -> {
            inventory.setItem(slot, item.getItem(player));
            inventory.setItemHandler(slot, (inv, s, oldItem, newItem, event) -> {
                item.getActions().forEach(action -> {
                    if (!item.isCanTake()) event.setCancelled();
                    if (item.isClose()) {
                        inventory.setCloseHandler(null);
                        inventory.close(player);
                    }
                    action.execute(player);
                });
            });
        });

        return inventory;
    }

    /**
     * Send chest to player
     * @param player Player 
     */
    public void send(Player player) {
        ChestSendEvent event = new ChestSendEvent(this, player);
        Server.getInstance().getPluginManager().callEvent(event);
            
        if (event.isCancelled()) {
            return;
        }

        openActions.forEach(action -> {
            action.execute(player);
        });

        ChestInventory inventory = this.build(player);
        ChestManager.getPlayerChest().put(player, new PlayerChest(this, inventory));
        player.addWindow(inventory);
    }
}
