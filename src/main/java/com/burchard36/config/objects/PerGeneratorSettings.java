package com.burchard36.config.objects;

import com.google.gson.annotations.SerializedName;

public class PerGeneratorSettings {

    @SerializedName(value = "spawn_particles_by_default")
    public boolean spawnParticles;

    @SerializedName(value = "toggle_spawn_particles_permission")
    public String changeSpawnParticlesTogglePermission;

    public PerGeneratorSettings() {
        this.spawnParticles = true;
        this.changeSpawnParticlesTogglePermission = "generators.spawnparticles.toggle";
    }

    public boolean spawnParticlesByDefault() {
        return this.spawnParticles;
    }
}
