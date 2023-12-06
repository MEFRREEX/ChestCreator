package com.mefrreex.chestcreator.utils;

import com.mefrreex.chestcreator.ChestCreator;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

public class Format {
    
    public static String format(String string, Player player) {
        String formatted = 
            string.replace("%player%", player.getName());

        if (ChestCreator.getPlaceholderAPI() != null) {
            formatted = ChestCreator.getPlaceholderAPI().translateString(formatted, player);
        }
        return TextFormat.colorize(formatted);
    }
}
