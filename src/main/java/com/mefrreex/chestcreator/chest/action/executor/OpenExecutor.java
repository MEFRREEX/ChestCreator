package com.mefrreex.chestcreator.chest.action.executor;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.Chest;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;

public class OpenExecutor implements Executor {

    @Override
    public void execute(Player player, String name) {
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
