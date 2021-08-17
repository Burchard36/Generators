package com.burchard36.utils.json;

import com.burchard36.MyPlugin;
import com.burchard36.utils.events.ConfigUpdateEvent;
import com.burchard36.utils.json.generators.Generator;
import com.burchard36.utils.json.generators.GeneratorConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class GeneratorManager implements Listener {

    private final MyPlugin plugin;
    private HashMap<String, GeneratorConfig> generatorConfigs;
    private HashMap<UUID, Generator> playerGenerators;

    public GeneratorManager(final MyPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onReload(final ConfigUpdateEvent e) {

    }

    private void setConfigValues() {
        this.generatorConfigs = new HashMap<>();
        final GeneratorConfig config = this.plugin.getConfigManager().getGeneratorConfig();


    }
}
