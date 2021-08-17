package com.burchard36;

import com.burchard36.utils.json.ConfigManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    public static boolean DEBUG = false;
    public static MyPlugin INSTANCE;
    private Gson gson;

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.configManager = new ConfigManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String ofString(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public final Gson getGson() {
        return this.gson;
    }

    public final ConfigManager getConfigManager() {
        return this.configManager;
    }
}
