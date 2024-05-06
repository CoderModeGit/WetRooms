package net.juyoh.wetrooms.world.gen;

import net.juyoh.wetrooms.world.dimension.ModDimensions;

public class ModWorldGeneration {
    public static void generateModWorlGen() {
        ModOreGeneration.generateOres();

        ModTreeGeneration.generateTrees();

    }
}
