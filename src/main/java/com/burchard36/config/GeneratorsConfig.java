package com.burchard36.config;

import com.burchard36.config.objects.GeneratorLevels;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.plugin.java.JavaPlugin;

public class GeneratorsConfig extends JsonDataFile {

    @JsonProperty("generator_levels")
    private GeneratorLevels levels;

    public GeneratorsConfig(JavaPlugin plugin,
                            String pathToFile,
                            FileFormat format) {
        super(plugin, pathToFile, format);

        this.levels = new GeneratorLevels();
    }
}
