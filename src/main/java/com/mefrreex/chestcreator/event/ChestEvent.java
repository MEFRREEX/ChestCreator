package com.mefrreex.chestcreator.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import com.mefrreex.chestcreator.chest.Chest;
import lombok.Getter;

@Getter
public abstract class ChestEvent extends Event implements Cancellable {
    
    private final Chest chest;

    public ChestEvent(Chest chest) {
        this.chest = chest;
    }
}
