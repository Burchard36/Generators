package com.burchard36.utils.json;

import com.burchard36.MyPlugin;
import com.burchard36.utils.Logger;
import com.burchard36.utils.events.ConfigUpdateEvent;
import com.burchard36.utils.json.generators.Generator;
import com.burchard36.utils.json.generators.GeneratorConfig;
import com.burchard36.utils.json.generators.GeneratorItemConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class GeneratorManager implements Listener {

    private final MyPlugin plugin;
    private HashMap<String, Generator> generatorConfigs;
    private HashMap<String, GeneratorItemConfig> generatorItemConfigs;

    public GeneratorManager(final MyPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onReload(final ConfigUpdateEvent e) {

    }

    private void setConfigValues() {
        this.generatorConfigs = new HashMap<>();
        this.generatorItemConfigs = new HashMap<>();
        final GeneratorConfig config = this.plugin.getConfigManager().getGeneratorConfig();

        config.getGenerators().forEach((gen) -> {
            if (this.generatorConfigs.containsKey(gen.generatorId)) {
                Logger.warn("Trying to load a generator with duplicate ID: " + gen.generatorId);
                return;
            }
            this.generatorConfigs.put(gen.generatorId, gen);
        });

    }
}
