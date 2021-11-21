package com.burchard36.managers.generators.data;

import com.burchard36.Logger;
import com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class JsonGeneratorData {
    @SerializedName(value = "location_x")
    public int x;

    @SerializedName(value = "location_y")
    public int y;

    @SerializedName(value = "location_z")
    public int z;

    @SerializedName(value =  "location_uuid")
    public String worldUuid;

    @SerializedName(value = "generator_level")
    public int level;

    @SerializedName(value = "generator_settings")
    public JsonGeneratorSettings generatorSettings;

    @SerializedName(value = "authorized_generator_owners")
    public List<String> authorizedOwnerUuids;

    @SerializedName(value = "owner_uuid")
    public String ownerUuid;

    public JsonGeneratorData(final Location location, final JsonGeneratorSettings settings) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.worldUuid = location.getWorld().getUID().toString();

    }

    public final Location getLocation() {
        final World world = Bukkit.getWorld(UUID.fromString(this.worldUuid));
        if (world == null) {
            Logger.error("&cError when loading world with UUID: &b:" + this.worldUuid + "&c. It does not exist!");
            return null;
        }

        return new Location(world, this.x, this.y, this.z);
    }

    public final Player getOwningPlayer() {
        return Bukkit.getPlayer(UUID.fromString(this.ownerUuid));
    }

    public final JsonGeneratorSettings getSettings() {
        return this.generatorSettings;
    }
}
