package com.burchard36.managers.generators.data;

import com.burchard36.GeneratorsPlugin;
import com.burchard36.json.JsonDataFile;
import com.burchard36.json.enums.FileFormat;

import java.util.ArrayList;
import java.util.List;

public class GeneratorsData extends JsonDataFile {

    public List<JsonGeneratorData> generators;

    public GeneratorsData() {
        super(GeneratorsPlugin.INSTANCE, "/data/generators.json", FileFormat.JSON);
        this.generators = new ArrayList<>();
    }

    public final List<JsonGeneratorData> getGenerators() {
        return this.generators;
    }
}
