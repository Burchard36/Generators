package com.burchard36.utils.json.generators;

import com.burchard36.MyPlugin;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class GeneratorConfig {

    public JsonArray generators;
    public JsonArray generatorItems;

    public List<Generator> getGenerators() {
        final List<Generator> generators = new ArrayList<>();
        final Gson gson = MyPlugin.INSTANCE.getGson();
        this.generators.forEach((gen) -> generators.add(gson.fromJson(gen, Generator.class)));
        return generators;
    }

    public List<GeneratorItemConfig> getGeneratorItems() {
        final List<GeneratorItemConfig> items = new ArrayList<>();
        final Gson gson = MyPlugin.INSTANCE.getGson();
        this.generatorItems.forEach((item) -> items.add(gson.fromJson(item, GeneratorItemConfig.class)));
        return items;
    }
}
