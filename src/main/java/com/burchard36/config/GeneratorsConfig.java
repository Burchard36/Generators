package com.burchard36.config;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.config.objects.GeneratorLevels;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GeneratorsConfig extends JsonDataFile {

    @Expose
    @SerializedName(value = "generator_levels")
    private List<GeneratorLevels> levels;

    public GeneratorsConfig() {
        super(GeneratorsPlugin.INSTANCE, "/config/generators.json",  FileFormat.JSON);

        this.levels = new ArrayList<>();
    }
}
