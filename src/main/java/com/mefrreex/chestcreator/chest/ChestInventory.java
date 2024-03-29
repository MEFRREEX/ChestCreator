package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import cn.nukkit.inventory.fake.FakeInventory;
import cn.nukkit.inventory.fake.FakeInventoryType;

import java.util.function.Consumer;

public class ChestInventory extends FakeInventory {

    private Consumer<Player> closeHandler;

    public ChestInventory(FakeInventoryType type, String title) {
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
