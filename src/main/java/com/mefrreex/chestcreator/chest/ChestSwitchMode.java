package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;
import com.mefrreex.chestcreator.utils.TriConsumer;

import java.util.Timer;
import java.util.TimerTask;

public enum ChestSwitchMode {
    REOPEN((player, playerChest, targetChest) -> {
        playerChest.inventory().close(player);
        new Timer().schedule(new TimerTask() {
            public void run() {
                targetChest.send(player);
            }
        }, 500);
    }),
    DEFAULT((player, playerChest, targetChest) -> targetChest.send(player));

    private final TriConsumer<Player, PlayerChest, Chest> processor;

    ChestSwitchMode(TriConsumer<Player, PlayerChest, Chest> processor) {
        this.processor = processor;
    }

    public void process(Player player, PlayerChest playerChest, Chest targetChest) {
        processor.accept(player, playerChest, targetChest);
    }
}
