package com.mefrreex.chestcreator.chest.action.executor;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.ChestManager;

public class OpenExecutor implements Executor {

    @Override
    public void execute(Player player, String name) {
        if (!ChestManager.exists(name)) {
            throw new RuntimeException("Chest with name " + name + " not found");
        }
        ChestManager.get(name).send(player);
    }

}
