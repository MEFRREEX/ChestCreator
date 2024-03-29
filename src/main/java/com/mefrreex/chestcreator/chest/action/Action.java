package com.mefrreex.chestcreator.chest.action;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.action.executor.Executor;
import com.mefrreex.chestcreator.chest.action.executor.ExecutorManager;
import com.mefrreex.chestcreator.utils.Format;

import lombok.Getter;
import lombok.ToString;

@Getter 
@ToString
public class Action {
    
    private String type;
    private String value;

    public Action(String type, String data) {
        this.type = type;
        this.value = data;
    }

    public String getType() {
        return type.toUpperCase();
    }

    public void execute(Player player) {
        Executor executor = ExecutorManager.getExecutor(this.getType());
        
        if (executor == null) {
            throw new RuntimeException("No registered Executor found for type " + this.getType());
        }
        
        executor.execute(player, Format.format(value, player));
    }
}
