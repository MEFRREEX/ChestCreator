package com.mefrreex.chestcreator.event;

import com.mefrreex.chestcreator.chest.Chest;

import cn.nukkit.event.HandlerList;

public class ChestLoadEvent extends ChestEvent {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public ChestLoadEvent(Chest form) {
        super(form);
    }
}
