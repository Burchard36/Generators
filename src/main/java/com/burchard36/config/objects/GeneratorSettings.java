package com.burchard36.config.objects;

import com.burchard36.config.enums.GenerationMode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneratorSettings {

    @SerializedName(value = "generation_mode")
    public String generateMode;

    @SerializedName(value = "generation_radius_in_chunks")
    public int chunkGenerationRadius;

    @SerializedName(value = "allow_per_generator_setting_changes")
    public boolean allowGeneratorSettingChanges;

    @SerializedName(value = "per_generator_setting_changes_permission")
    public String perGeneratorSettingChangePermission;

    @Expose
    @SerializedName(value = "per_generator_settings")
    public PerGeneratorSettings perGeneratorSettings;

    public GeneratorSettings() {
        this.generateMode = GenerationMode.OWNER_OR_AUTHORIZED_ONLINE.name();
        this.chunkGenerationRadius = 3;
        this.allowGeneratorSettingChanges = true;
        this.perGeneratorSettingChangePermission = "generators.settings.change";
        this.perGeneratorSettings = new PerGeneratorSettings();
    }

    public final GenerationMode getGenerationMode() {
        return GenerationMode.valueOf(this.generateMode);
    }
}
