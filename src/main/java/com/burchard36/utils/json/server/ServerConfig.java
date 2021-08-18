package com.burchard36.utils.json.server;

import com.burchard36.MyPlugin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ServerConfig {

    public JsonObject player;
    public JsonObject server;

    public PlayerConfig getPlayerConfig() {
        final Gson gson = MyPlugin.INSTANCE.getGson();
        return gson.fromJson(this.player, PlayerConfig.class);
    }

    public ServerSettings getServerSettings() {
        final Gson gson = MyPlugin.INSTANCE.getGson();
        return gson.fromJson(this.server, ServerSettings.class);
    }
}
