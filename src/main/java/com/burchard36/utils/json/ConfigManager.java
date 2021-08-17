package com.burchard36.utils.json;

import com.burchard36.MyPlugin;
import com.burchard36.utils.Logger;
import com.burchard36.utils.events.ConfigUpdateEvent;
import com.burchard36.utils.json.generators.GeneratorConfig;
import com.burchard36.utils.json.server.ServerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigManager implements Listener {

    private final MyPlugin plugin;
    private GeneratorConfig generatorConfig;
    private ServerConfig serverConfig;

    public ConfigManager(final MyPlugin plugin) {
        this.plugin = plugin;
        this.setConfigValues();
    }

    @EventHandler
    public void onReload(final ConfigUpdateEvent e) {
        Logger.debug("Reloading config values for ConfigManager. . .");
        this.setConfigValues();
    }

    private void setConfigValues() {
        this.ensureDirectories();
        final File serverSettingsFile = new File(this.plugin.getDataFolder(), "/config/serverSettings.json");
        final File generatorsSettingsFile = new File(this.plugin.getDataFolder(), "/config/generators.json");

        this.ensureFiles(Arrays.asList(serverSettingsFile, generatorsSettingsFile));

        try {

            Reader reader = Files.newBufferedReader(serverSettingsFile.toPath());
            this.serverConfig = this.plugin.getGson().fromJson(reader, ServerConfig.class);

            reader = Files.newBufferedReader(generatorsSettingsFile.toPath());
            this.generatorConfig = this.plugin.getGson().fromJson(reader, GeneratorConfig.class);

        } catch (final IOException ex) {
            Logger.error("Error when setting config values: ");
            ex.printStackTrace();
        }
    }

    private void ensureDirectories() {
        final File file = new File(this.plugin.getDataFolder(), "/config");

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Logger.log("Successfully created new file named: &b" + file.getName());
                }
            } catch (final IOException ex) {
                Logger.error("Error while trying to ensureDirectories: ");
                ex.printStackTrace();
            }
        }
    }

    public void ensureFiles(final List<File> files) {
        files.forEach((file) -> {
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        this.plugin.saveResource("config/" + file.getName(), false);
                    }
                }catch (final IOException ex) {
                    Logger.error("Error when creating config file named: " + file.getName());
                    ex.printStackTrace();
                }
            }
        });
    }

    public final ServerConfig getServerConfig() {
        return this.serverConfig;
    }

    public final GeneratorConfig getGeneratorConfig() {
        return this.generatorConfig;
    }
}
