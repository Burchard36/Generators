package com.burchard36.lib;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.managers.generators.data.JsonGeneratorData;
import com.burchard36.managers.generators.data.JsonGeneratorSettings;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Generator {

    private JsonGeneratorData generatorData;
    private BukkitTask runningTask;

    public Generator(final JsonGeneratorData data) {
        this.generatorData = data;

        this.runningTask = new BukkitRunnable() {
            @Override
            public void run() {

            }
        }.runTaskTimer(GeneratorsPlugin.INSTANCE, 0, )
    }


    public void stopTask() {

    }

    public final void addLevel() {
        this.generatorData.level++;
    }

    public final void setLevel(final int level) {
        this.generatorData.level = level;
    }

    public final int getgeneratorLevel() {
        return this.generatorData.level;
    }

    public final JsonGeneratorSettings getGeneratorSettings() {
        return this.generatorData.generatorSettings;
    }

    public final Location getGeneratorLocation() {
        return this.generatorData.getLocation();
    }

    public final JsonGeneratorData getData() {
        return this.generatorData;
    }

}
