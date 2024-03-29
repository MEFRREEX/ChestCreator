package com.mefrreex.chestcreator.chest.action.executor.impl;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.action.executor.Executor;

public class MessageExecutor implements Executor {

    private final boolean isToPlayer;

    public MessageExecutor(boolean isToPlayer) {
        this.isToPlayer = isToPlayer;
    }

    @Override
    public void execute(Player player, String message) {
        if (isToPlayer) {
            player.sendMessage(message);
        } else {
            player.chat(message);
        }
    }
}
