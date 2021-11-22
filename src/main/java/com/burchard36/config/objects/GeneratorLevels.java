package com.burchard36.config.objects;

import com.burchard36.inventory.ItemWrapper;
import com.google.gson.annotations.SerializedName;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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

    @SerializedName(value = "this_level")
    public int thisLevel;

    @SerializedName(value = "item_name")
    public String itemName;

    @SerializedName(value = "item_lore")
    public List<String> itemLore;

    @SerializedName(value = "level_costs")
    public HashMap<String, Integer> levelCosts;

    public GeneratorLevels() {
        this.materialType = Material.END_PORTAL_FRAME.name();
        this.generatedMaterials = new ArrayList<>();
        this.thisLevel = 1;
        this.nextLevel = 2;
        this.levelCosts = new HashMap<>();
        this.itemName = "&a&lDefault Json Value";
        this.itemLore = new ArrayList<>();
    }

    public GeneratorLevels(final String itemName,
                           final List<String> itemLore,
                           final Material itemMaterial,
                           final int thisLevel,
                           final int nextLevel,
                           final List<Material> generatedMaterials,
                           final HashMap<String, Integer> levelCosts) {

        this.itemName = itemName;
        this.itemLore = itemLore;
        this.materialType = itemMaterial.name();
        this.thisLevel = thisLevel;
        this.nextLevel = nextLevel;
        final List<String> generatedMaterialsConverted = new ArrayList<>();
        generatedMaterials.forEach((genMat) -> {
            generatedMaterialsConverted.add(genMat.name());
        });

        this.generatedMaterials = generatedMaterialsConverted;
        this.levelCosts = levelCosts;
    }

    public final ItemWrapper getItemWrapper() {
        final ItemWrapper wrapper = new ItemWrapper(new ItemStack(Material.valueOf(this.materialType)));
        wrapper.setDisplayName(this.itemName);
        wrapper.setItemLore(this.itemLore);
        wrapper.addDataInteger("generator_current_level", this.thisLevel);
        return wrapper;
    }

}
