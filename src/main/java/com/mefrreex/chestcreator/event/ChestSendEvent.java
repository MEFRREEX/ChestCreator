package com.mefrreex.chestcreator.event;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import com.mefrreex.chestcreator.chest.Chest;
import lombok.Getter;

@Getter
public class ChestSendEvent extends ChestEvent {

    private final Player player;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public ChestSendEvent(Chest form, Player player) {
        super(form);
        this.player = player;
    }
}
