package com.mefrreex.chestcreator.chest;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.utils.Config;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mefrreex.chestcreator.ChestCreator;
import com.mefrreex.chestcreator.event.ChestLoadEvent;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ChestManager {
    
    private static final Map<String, Chest> chests = new HashMap<>();

    @Getter
    private static final Map<Player, PlayerChest> playerChest = new HashMap<>();

    private static final Gson gson = new Gson();

    /**
     * Get chests folder
     * @return File
     */
    public static File getChestsFolder() {
        return new File(ChestCreator.get().getDataFolder() + "/chests");
    }

    /**
     * Get chest by name
     * @param name chest name
     * @return chest
     */
    public static Chest get(String name) {
        return chests.get(name);
    }

    /**
     * Check the existence of the chest
     * @param name chest name
     */
    public static boolean exists(String name) {
        return chests.containsKey(name);
    }

    /**
     * Load chest
     * @param name File
     * @param file chest name
     */
    public synchronized static void load(String name, File file) {
        try {
            Chest chest = gson.fromJson(new FileReader(file, Charset.forName("UTF-8")), Chest.class);

            ChestLoadEvent event = new ChestLoadEvent(chest);
            Server.getInstance().getPluginManager().callEvent(event);
            
            if (event.isCancelled()) {
                return;
            }

            chest.init();
            chests.put(name, chest);
        } catch(IOException | JsonSyntaxException | JsonIOException e) {
            throw new RuntimeException("Failed to load the chest " + file.getName(), e);
        }
    }

    /**
     * Load all chests
     */
    public static void loadAll() {
        Config config = new Config(ChestCreator.get().getDataFolder() + "/chests.yml");
        for (String name : config.getSection("chests").getKeys(false)) {
            String path = config.getString("chests." + name);
            File file = new File(getChestsFolder() + "/" + path);
            load(name, file);
        }
    }

    public record PlayerChest(Chest chest, Inventory inventory) {
    
    }
}
