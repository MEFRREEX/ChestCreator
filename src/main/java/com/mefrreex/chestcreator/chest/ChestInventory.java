package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import cn.nukkit.inventory.InventoryType;
import me.iwareq.fakeinventories.FakeInventory;

import java.util.function.Consumer;

public class ChestInventory extends FakeInventory {

    private Consumer<Player> closeHandler;

    public ChestInventory(InventoryType type, String title) {
        super(type, title);
    }
    
    public ChestInventory setCloseHandler(Consumer<Player> closeHandler) {
        this.closeHandler = closeHandler;
        return this;
    }

    @Override
    public void onClose(Player player) {
        super.onClose(player);

        if (this.closeHandler != null) {
            this.closeHandler.accept(player);
        }
    }
}
