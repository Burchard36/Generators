package com.burchard36;

import com.burchard36.config.GeneratorsConfig;
import com.burchard36.json.PluginDataMap;
import com.burchard36.json.enums.FileFormat;
import org.bukkit.plugin.java.JavaPlugin;

public final class Generators extends JavaPlugin {

    private ApiLib lib;
    private GeneratorsConfig generatorsConfig;

    @Override
    public void onEnable() {
        this.lib = new ApiLib().initializeApi(this);

        this.lib.getPluginDataManager().registerPluginMap(Configs.GENERATORS, new PluginDataMap());
        this.generatorsConfig = new GeneratorsConfig(this, "/config/generators", FileFormat.JSON);

        this.lib.getPluginDataManager().loadDataFileToMap(Configs.GENERATORS, "generator_config", this.generatorsConfig);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
