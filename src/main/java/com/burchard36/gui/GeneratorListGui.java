package com.burchard36.gui;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.config.GeneratorsConfig;
import com.burchard36.inventory.ClickableItem;
import com.burchard36.inventory.ItemWrapper;
import com.burchard36.inventory.PluginInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorListGui {

    private final List<ItemWrapper> wrappers;
    private final HashMap<Integer, PluginInventory> inventories;

    public GeneratorListGui(final GeneratorsConfig config) {
        this.inventories = new HashMap<>();
        this.wrappers = new ArrayList<>();

        config.getLevelsList().forEach((jsonGeneratorConfig) -> this.wrappers.add(jsonGeneratorConfig.getItemWrapper()));

        int currentPage = 0;
        List<ClickableItem> clickableItemsPage = new ArrayList<>();
        for (int x = 0; x <= (this.wrappers.size() - 1); x++) {

            clickableItemsPage.add(this.getClickableItem(this.wrappers.get(x)));

            if (x >= 44) {
                final PluginInventory inventory = this.createInventory();
                inventory.addClickableItems(clickableItemsPage);
                this.inventories.put(currentPage, inventory);
                clickableItemsPage.clear();
                currentPage++;
            }
        }

        // handle excess
        final PluginInventory inventory = this.createInventory();
        inventory.addClickableItems(clickableItemsPage);
        inventory.register(GeneratorsPlugin.INSTANCE);
        this.inventories.put(currentPage, inventory);
    }

    private PluginInventory createInventory() {
        return new PluginInventory(54, "&b&lGenerators")
                .onClick((onClick) -> onClick.setCancelled(true));
    }

    private ClickableItem getClickableItem(final ItemWrapper wrapper) {
        final ClickableItem item = new ClickableItem(wrapper);
        item.onClick((clickAction) -> {
            clickAction.setCancelled(true);
            final Player clicker = (Player) clickAction.getWhoClicked();
            if (clicker.hasPermission("generators.give.self")) {

                final ItemStack clickedItem = clickAction.getCurrentItem();
                if (clickedItem == null) return;

                if (clickAction.isShiftClick() && clickAction.isLeftClick()) {
                    clickedItem.setAmount(64);
                } else if (clickAction.isShiftClick() && clickAction.isRightClick()) {
                    clickedItem.setAmount(32);
                } else if (clickAction.isRightClick()) {
                    clickedItem.setAmount(16);
                }

                clicker.getInventory().addItem(clickedItem);
            }
        });

        return item;
    }

    public final PluginInventory getInventoryPage(final int page) {
        final PluginInventory inventory = this.inventories.get(page);
        if (inventory == null) return null;

        // 45

        final ItemWrapper backgroundWrapper = new ItemWrapper(new ItemStack(Material.BLACK_STAINED_GLASS_PANE))
                .setDisplayName("&f ");
        final ClickableItem backgroundItem = new ClickableItem(backgroundWrapper);

        Material backButtonMaterial = Material.ARROW;
        String backButtonName = "&e&l<<";
        if (page == 0) {
            backButtonMaterial = Material.BARRIER;
            backButtonName = "&cYou can't go back!";
        }
        final ItemWrapper backButtonWrapper = new ItemWrapper(new ItemStack(backButtonMaterial))
                .setDisplayName(backButtonName);
        final ClickableItem backButtonItem = new ClickableItem(backButtonWrapper);

        Material forwardButtonMaterial = Material.ARROW;
        String forwardButtonName = "&e&l>>";
        if ((this.inventories.size() - 1) == page) {
            forwardButtonMaterial = Material.BARRIER;
            forwardButtonName = "&cYou can't go forward!";
        }
        final ItemWrapper forwardButtonWrapper = new ItemWrapper(new ItemStack(forwardButtonMaterial))
                .setDisplayName(forwardButtonName);
        final ClickableItem forwardButtonItem= new ClickableItem(forwardButtonWrapper);

        inventory.addClickableItemAtSlot(45, backgroundItem);
        inventory.addClickableItemAtSlot(46, backgroundItem);
        inventory.addClickableItemAtSlot(47, backgroundItem);
        inventory.addClickableItemAtSlot(48, backButtonItem);
        inventory.addClickableItemAtSlot(49, backgroundItem);
        inventory.addClickableItemAtSlot(50, forwardButtonItem);
        inventory.addClickableItemAtSlot(51, backgroundItem);
        inventory.addClickableItemAtSlot(52, backgroundItem);
        inventory.addClickableItemAtSlot(53, backgroundItem);
        return inventory;
    }
}
