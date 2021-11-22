package com.burchard36.managers.generators;

import com.burchard36.Configs;
import com.burchard36.GeneratorsPlugin;
import com.burchard36.inventory.ItemWrapper;
import com.burchard36.lib.Generator;
import com.burchard36.managers.Manager;
import com.burchard36.managers.generators.data.GeneratorsData;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GeneratorManager implements Manager {

    private final GeneratorsPlugin plugin;
    private GeneratorsData generatorsData;
    private HashMap<Location, Generator> runningGenerators;

    public GeneratorManager(final GeneratorsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        this.plugin.getLib().getPluginDataManager()
                .loadDataFileToMap(Configs.GENERATORS, "generators_data", new GeneratorsData());
        this.generatorsData = (GeneratorsData) this.plugin.getLib().getPluginDataManager().getDataFileFromMap(Configs.GENERATORS, "generators_data");

        this.generatorsData.getGenerators()
                .forEach((jsonGenerator) ->
                        this.runningGenerators.putIfAbsent(jsonGenerator.getLocation(), new Generator(jsonGenerator)));


        this.runningGenerators = new HashMap<>();
    }

    @Override
    public void unload() {

        this.runningGenerators.clear();
    }

    @Override
    public void reload() {
        this.unload();
        this.load();
    }

    public final GeneratorsPlugin getPlugin() {
        return this.plugin;
    }

    private static class Listeners implements Listener {


        public Listeners() {

        }

        @EventHandler
        public void onBlockPlace(final BlockPlaceEvent e) {
            final ItemWrapper placed = new ItemWrapper(e.getItemInHand());

        }

        @EventHandler
        public void onBlockBreak(final BlockBreakEvent e) {

        }
    }
}
