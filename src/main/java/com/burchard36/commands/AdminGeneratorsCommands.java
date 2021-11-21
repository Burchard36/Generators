package com.burchard36.commands;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.command.ApiCommand;

import java.util.ArrayList;

public class AdminGeneratorsCommands {

    private final GeneratorsPlugin plugin;
    private final ApiCommand command;

    public AdminGeneratorsCommands(final GeneratorsPlugin plugin) {
        this.plugin = plugin;
        this.command = new ApiCommand("generators",
                "/generators",
                "Allows admins to view all generators and pull them from the inventory!",
                new ArrayList<>()).onPlayerSender((playerSent) -> {

        });
    }
}
