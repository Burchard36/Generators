package com.burchard36.utils.json;

import com.burchard36.MyPlugin;
import com.burchard36.utils.Logger;
import com.burchard36.utils.events.ConfigUpdateEvent;
import com.burchard36.utils.json.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;

public class DataManager implements Listener {

    private final MyPlugin plugin;
    private HashMap<UUID, PlayerData> playerData;

    public DataManager(final MyPlugin plugin) {
        this.plugin = plugin;
        this.setConfigValues();

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onConfigReload(final ConfigUpdateEvent e) {
        this.setConfigValues();
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {

    }

    private void setConfigValues() {
        this.playerData = new HashMap<>();
    }

    public void loadPlayerData(final UUID playerUuid) {
        if (this.playerData.containsKey(playerUuid)) return;
        final File playerDataFile = this.getPlayerDataFile(playerUuid);
        if (!playerDataFile.exists()) {

        } else {
            try {
                final FileWriter writer = new FileWriter(playerDataFile);
            } catch (final IOException ex) {
                Logger.error("Error when trying to open a new BufferedReader when creating a new PlayerData file.");
                ex.printStackTrace();
            }
        }
    }

    public final File getPlayerDataFile(final UUID uuid) {
        return new File(this.plugin.getDataFolder(), "data/" + uuid.toString() + ".json");
    }

    public final void savePlayerData(final UUID uuid, final PlayerData data) {
        final File file = this.getPlayerDataFile(uuid);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Logger.debug("Successfully created new PlayerData file for Player with UUID: " + uuid.toString());
                    this.writeData(file, this.getDefaultData());
                    return;
                }
            } catch (final IOException ex) {
                Logger.error("Error when trying to load data file!");
                ex.printStackTrace();
            }
        }

        this.writeData(file, data);
    }

    private void writeData(final File file, final PlayerData data) {
        try {
            final FileWriter writer = new FileWriter(file);
            this.plugin.getGson().toJson(this.getDefaultData(), writer);
            writer.flush();
            writer.close();
        } catch (final IOException ex) {
            Logger.error("Error when trying to write to player data file!");
            ex.printStackTrace();
        }
    }

    public final void updatePlayerCache(final UUID uuid, final PlayerData data) {
        this.playerData.replace(uuid, data);
    }

    private PlayerData getDefaultData() {
        return new PlayerData(this.plugin);
    }
}
