package com.burchard36.utils.json.player;

import com.burchard36.MyPlugin;
import com.burchard36.utils.json.server.ServerConfig;
import com.google.gson.JsonArray;

public class PlayerData {

    public JsonArray playerGenerators;
    public int maxGenerators;

    private transient MyPlugin plugin;

    public PlayerData(final MyPlugin plugin) {
        this.plugin = plugin;
        final ServerConfig config = this.plugin.getConfigManager().getServerConfig();
        this.playerGenerators = new JsonArray();
        this.maxGenerators = config.getPlayerConfig().maxStartingGenerators;
    }

    public void load(final MyPlugin plugin) {

    }
}
