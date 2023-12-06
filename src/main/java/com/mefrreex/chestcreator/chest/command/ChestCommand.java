package com.mefrreex.chestcreator.chest.command;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ChestCommand {
    
    @SerializedName("enable") 
    private boolean enable = true;

    @SerializedName("name") 
    private String name;
    
    @SerializedName("description") 
    private String description = "";
    
    @SerializedName("aliases") 
    private String[] aliases = new String[]{};

    @SerializedName("permission") 
    private String permission;

    public ChestCommand(String name) {
        this(name, name);
    }

    public ChestCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
