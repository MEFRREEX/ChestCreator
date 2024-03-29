package com.mefrreex.chestcreator.chest.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class ChestCommand {
    
    private boolean enable = true;

    private String name;
    
    private String description = "";
    
    private String[] aliases = new String[0];

    private String permission;

    public ChestCommand(String name) {
        this(name, name);
    }

    public ChestCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
