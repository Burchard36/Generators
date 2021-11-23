package com.burchard36.commands;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.Logger;
import com.burchard36.command.ApiCommand;
import com.burchard36.gui.GeneratorListGui;
import com.burchard36.inventory.PluginInventory;
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
                    final PluginInventory inventory = gui.getInventoryPage(0);

                    if (inventory == null) {
                        Logger.error("Could not find page 0 of Generators List!");
                        player.sendMessage(convert("&cAn internal error occured, please contact an admin or developer."));
                        return;
                    }
                    inventory.open(player);
                });
    }

    public final ApiCommand getCommand() {
        return this.command;
    }

}
