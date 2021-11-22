package com.burchard36;

import com.burchard36.commands.GeneratorsListCommand;
import com.burchard36.config.GeneratorsConfig;
import com.burchard36.json.PluginDataMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeneratorsPlugin extends JavaPlugin implements Api {

    public static GeneratorsPlugin INSTANCE;
    private ApiLib lib;
    private GeneratorsConfig generatorsConfig;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.lib = new ApiLib().initializeApi(this);
        ApiLib.registerCommand(new GeneratorsListCommand(this).getCommand());

        this.lib.getPluginDataManager().registerPluginMap(Configs.GENERATORS, new PluginDataMap(this.lib.getPluginDataManager().getJsonWriter()));
        this.generatorsConfig = new GeneratorsConfig();

        this.lib.getPluginDataManager()
                .loadDataFileToMap(Configs.GENERATORS, "generators_config", this.generatorsConfig);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean isDebug() {
        return false;
    }

    public ApiLib getLib() {
        return this.lib;
    }

    public GeneratorsConfig getGeneratorsConfig() {
        return this.generatorsConfig;
    }
}
