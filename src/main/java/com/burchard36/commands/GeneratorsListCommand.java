package com.burchard36.commands;

import com.burchard36.command.ApiCommand;

import java.util.ArrayList;

public class GeneratorsListCommand {

    private final ApiCommand command;

    public GeneratorsListCommand() {
        this.command = new ApiCommand("genlist", "Lists all generators", "/genlist", new ArrayList<>())
                .onPlayerSender((playerSent) -> {

                });
    }

    public final ApiCommand getCommand() {
        return this.command;
    }

}
