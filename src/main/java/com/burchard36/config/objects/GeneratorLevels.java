package com.burchard36.config.objects;

import com.google.gson.annotations.SerializedName;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorLevels {

    @SerializedName(value = "material")
    public String materialType;

    @SerializedName(value = "generated_materials")
    public List<String> generatedMaterials;

    @SerializedName(value = "next_level")
    public int nextLevel;

    @SerializedName(value = "level_costs")
    public HashMap<String, Integer> levelCosts;

    public GeneratorLevels() {
        this.materialType = Material.END_PORTAL_FRAME.name();
        this.generatedMaterials = new ArrayList<>();
        this.nextLevel = 2;
        this.levelCosts = new HashMap<>();
    }

}
