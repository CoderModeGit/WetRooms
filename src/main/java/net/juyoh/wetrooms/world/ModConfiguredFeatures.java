package net.juyoh.wetrooms.world;

import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> EPSOM_ORE_KEY = registryKey("epsomite_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RUBBER_KEY = registryKey("rubber");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> epsomiteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.EPSOMITE_ORE.getDefaultState()));

        register(context, EPSOM_ORE_KEY, Feature.ORE,  new OreFeatureConfig(epsomiteOres, 6));
        register(context, RUBBER_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.RUBBER_LOG),
                new ForkingTrunkPlacer(5, 4, 3),
                BlockStateProvider.of(ModBlocks.RUBBER_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),

                new TwoLayersFeatureSize(1, 0, 4)).build()
        );
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(WetRooms.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
