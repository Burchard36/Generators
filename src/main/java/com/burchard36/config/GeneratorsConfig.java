package com.burchard36.config;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.config.objects.GeneratorLevels;
import com.burchard36.config.objects.GeneratorSettings;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorsConfig extends JsonDataFile {

    @Expose
    @SerializedName(value = "generator_levels")
    private List<GeneratorLevels> levels;

    @Expose
    @SerializedName(value = "generator_settings")
    public GeneratorSettings settings;

    public GeneratorsConfig() {
        super(GeneratorsPlugin.INSTANCE, "/config/generators.json",  FileFormat.JSON);

        this.levels = new ArrayList<>();
        this.settings = new GeneratorSettings();

        this.levels.add(new GeneratorLevels("&e&l&oGenerator &7(&b&l1&7)",
                new ArrayList<>(),
                Material.END_PORTAL_FRAME,
                1,
                2,
                new ArrayList<>(),
                new HashMap<>()));

        this.levels.add(new GeneratorLevels("&e&l&oGenerator &7(&b&l2&7)",
                new ArrayList<>(),
                Material.END_PORTAL_FRAME,
                2,
                3,
                new ArrayList<>(),
                new HashMap<>()));
    }

    public final List<GeneratorLevels> getLevelsList() {
        return this.levels;
    }
}
