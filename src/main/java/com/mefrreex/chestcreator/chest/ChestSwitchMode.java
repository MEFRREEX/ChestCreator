package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.ChestManager.PlayerChest;

import java.util.Timer;
import java.util.TimerTask;

public enum ChestSwitchMode {
    REOPEN {
        @Override
        public void process(Player player, PlayerChest playerChest, Chest targetChest) {
            playerChest.inventory().close(player);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    targetChest.send(player);
                }
            }, 500);
        }
    },
    DEFAULT {
        @Override
        public void process(Player player, PlayerChest playerChest, Chest targetChest) {
            targetChest.send(player);
        }
    };

    public abstract void process(Player player, PlayerChest playerChest, Chest targetChest);
}
