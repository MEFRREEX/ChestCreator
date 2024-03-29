package com.mefrreex.chestcreator.chest.action.executor.impl;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.Chest;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;
import com.mefrreex.chestcreator.chest.action.executor.Executor;
import com.mefrreex.formcreator.FormCreator;

public class OpenChestExecutor implements Executor {

    private final boolean oldType;

    public OpenChestExecutor(boolean oldType) {
        this.oldType = oldType;
    }

    @Override
    public void execute(Player player, String name) {
        if (oldType) {
            FormCreator.get().getLogger().warning("Uses the deprecated executor type \"OPEN\". Change it to \"OPEN_CHEST\"");
        }
        
        if (!ChestManager.exists(name)) {
            throw new RuntimeException("Chest with name " + name + " not found");
        }

        Chest chest = ChestManager.get(name);

        PlayerChest playerChest = ChestManager.getPlayerChest().get(player);
        if (playerChest != null) {
            playerChest.chest().getSwitchMode().process(player, playerChest, chest);
        } else {
            chest.send(player);
        }
    }
}
