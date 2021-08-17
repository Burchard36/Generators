package com.burchard36;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    public static boolean DEBUG = false;
    private Gson gson;

    @Override
    public void onEnable() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

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
}
