package com.burchard36.utils.json.player;

import com.burchard36.MyPlugin;
import com.burchard36.utils.json.server.ServerConfig;
import com.google.gson.JsonArray;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    public String uuid;
    public JsonArray playerGenerators;
    public int maxGenerators;

    private transient MyPlugin plugin;

    public PlayerData(final MyPlugin plugin,
                       final UUID uuid) {
        this.plugin = plugin;
        final ServerConfig config = this.plugin.getConfigManager().getServerConfig();

        this.uuid = uuid.toString();
        this.playerGenerators = new JsonArray();
        this.maxGenerators = config.getPlayerConfig().maxStartingGenerators;
    }

    public void load(final MyPlugin plugin) {

    }


    public final UUID getPlayerUuid() {
        return UUID.fromString(this.uuid);
    }

    public final Player getOnlinePlayer() {
        return Bukkit.getPlayer(this.getPlayerUuid());
    }

    public final OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.getPlayerUuid());
    }
}
