package com.burchard36.config.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorLevels {

    @JsonProperty("material")
    public String materialType;

    @JsonProperty("generated_materials")
    public List<String> generatedMaterials;

    @JsonProperty("next_level")
    public int nextLevel;

    @JsonProperty("level_costs")
    public HashMap<String, Integer> levelCosts;

    public GeneratorLevels() {
        this.materialType = Material.END_PORTAL_FRAME.name();
        this.generatedMaterials = new ArrayList<>();
        this.nextLevel = 2;
        this.levelCosts = new HashMap<>();
    }

}
