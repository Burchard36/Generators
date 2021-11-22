package com.burchard36.commands;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.command.ApiCommand;
import com.burchard36.gui.GeneratorListGui;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.burchard36.ApiLib.convert;

public class GeneratorsListCommand {

    private final GeneratorsPlugin plugin;
    private final ApiCommand command;

    public GeneratorsListCommand(final GeneratorsPlugin plugin) {
        this.plugin = plugin;
        this.command = new ApiCommand("genlist", "Lists all generators", "/genlist", new ArrayList<>())
                .onPlayerSender((playerSent) -> {
                    final String listGeneratorsPermission = "generators.list";
                    final Player player = playerSent.getSendingPlayer();

                    if (!player.hasPermission(listGeneratorsPermission)) {
                        player.sendMessage(convert("&cYou do not have permission to do this!"));
                        return;
                    }

                    final GeneratorListGui gui = new GeneratorListGui(this.plugin.getGeneratorsConfig());
                    gui.getInventoryPage(0).open(player);
                });
    }

    public final ApiCommand getCommand() {
        return this.command;
    }

}
