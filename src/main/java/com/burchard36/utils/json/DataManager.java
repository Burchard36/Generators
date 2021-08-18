package com.burchard36.utils.json;

import com.burchard36.MyPlugin;
import com.burchard36.utils.Logger;
import com.burchard36.utils.events.ConfigUpdateEvent;
import com.burchard36.utils.json.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
        new BukkitRunnable() {
            @Override
            public void run() {
                setConfigValues();
            }
        }.runTaskAsynchronously(this.plugin);
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (this.getPlayerData(p.getUniqueId()) == null) {
            Logger.debug("Player with UUID: " + p.getUniqueId() + " joined and does not have a data file, creating. . .");
            this.loadPlayerData(p.getUniqueId());
        }
    }

    private void setConfigValues() {
        this.playerData = new HashMap<>();

        File[] files = new File(this.plugin.getDataFolder() + "/data").listFiles();
        Logger.log("Loading player data from " + this.plugin.getDataFolder().getName() + "/data...");
        for (final File file : files) {
            if (!file.getName().endsWith(".json")) return;
            this.loadPlayerData(UUID.fromString(file.getName().replace(".json", "")));
        }
        Logger.log("Successfully loaded " + this.playerData.size() + " PlayerData entries");
    }

    public void loadPlayerData(final UUID playerUuid) {
        if (this.playerData.containsKey(playerUuid)) return;
        final File playerDataFile = this.getPlayerDataFile(playerUuid);
        if (!playerDataFile.exists()) {
            this.savePlayerData(playerUuid, this.getDefaultData(playerUuid));
            return;
        }

        try {
            final Reader reader = Files.newBufferedReader(playerDataFile.toPath());
            final PlayerData data = this.plugin.getGson().fromJson(reader, PlayerData.class);
            this.playerData.put(playerUuid, data);
            Logger.debug("Successfully loaded PlayerData for player with UUID: " + playerUuid);
            reader.close();
        } catch (final IOException ex) {
            Logger.error("Error when trying to load player with UUID: " + playerUuid);
            ex.printStackTrace();
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
                    this.writeData(file, this.getDefaultData(uuid));
                    this.loadPlayerData(uuid);
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
            this.plugin.getGson().toJson(data, writer);
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

    private PlayerData getDefaultData(final UUID uuid) {
        return new PlayerData(this.plugin, uuid);
    }

    public final PlayerData getPlayerData(final UUID uuid) {
        return this.playerData.get(uuid);
    }
}
