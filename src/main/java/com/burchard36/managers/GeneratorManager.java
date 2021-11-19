package com.burchard36.managers;

import com.burchard36.Generators;

public class GeneratorManager implements Manager {

    public Generators plugin;

    public GeneratorManager(final Generators plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {

    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }
}
