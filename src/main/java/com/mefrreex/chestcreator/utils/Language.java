package com.mefrreex.chestcreator.utils;

import com.mefrreex.chestcreator.ChestCreator;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class Language {

    private static Config messages;
    
    /**
     * Load language
     */
    public static void init(ChestCreator main) {
        String lang = main.getConfig().getString("language", "eng").toLowerCase();
        main.saveResource("lang/" + lang + ".yml");
        messages = new Config(main.getDataFolder() + "/lang/" + lang + ".yml", Config.YAML);
    }

    /**
     * Get message
     * @param key          Message key
     * @param replacements Replacements
     * @return String message
     */
    public static String get(String key, Object... replacements) {
        String message = TextFormat.colorize(messages.getString(key, "null"));
        int i = 0;
        for (Object replacement : replacements) {
            message = message.replace("[" + i + "]", String.valueOf(replacement));
            i++;
        }
        return message;
    }
}
