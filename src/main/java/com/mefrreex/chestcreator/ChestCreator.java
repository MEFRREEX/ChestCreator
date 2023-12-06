package com.mefrreex.chestcreator;

import cn.nukkit.plugin.PluginBase;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.chestcreator.chest.action.executor.ExecutorManager;
import com.mefrreex.chestcreator.command.ChestCreatorCommand;
import com.mefrreex.chestcreator.metrics.Metrics;
import com.mefrreex.chestcreator.utils.Language;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

import java.io.File;
import java.io.IOException;

public class ChestCreator extends PluginBase {
    
    private static ChestCreator instance;

    private static PlaceholderAPI placeholderApi;

    @Override
    public void onLoad() {
        ChestCreator.instance = this;
        this.saveDefaultConfig();
        this.saveResource("chests.yml");
        this.setup();
        this.metrics();
    }

    @Override
    public void onEnable() {
        Language.init(this);
        ExecutorManager.init();
        ChestManager.loadAll();
        ChestCreatorCommand.register();
        this.loadPlaceholders();
    }

    private void setup() {
        File firstStartFile = new File(getDataFolder() + "/.configured");
        if (!firstStartFile.exists()) {
            this.saveResource("chests/example.json");
            try {
                firstStartFile.createNewFile();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    private void loadPlaceholders() {
        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeholderApi = PlaceholderAPI.getInstance();
        }
    }

    public void metrics() {
        int pluginId = 19705;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("server_movement", () -> String.valueOf(this.getConfig().getBoolean("PowerNukkiX-movement-server"))));
        metrics.addCustomChart(new Metrics.SimplePie("nukkit_version", () -> this.getServer().getNukkitVersion()));
    }

    /** Instance */
    public static ChestCreator get() {
        return instance;
    }

    public static PlaceholderAPI getPlaceholderAPI() {
        return placeholderApi;
    }
}
