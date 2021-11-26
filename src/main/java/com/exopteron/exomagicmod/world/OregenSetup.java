package com.exopteron.exomagicmod.world;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.items.MagiciumSetup;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class OregenSetup {
    private static ConfiguredFeature<?, ?> ORE_MAGICIUM_END = Feature.ORE.configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), MagiciumSetup.MAGICIUM_ORE.getDefaultState(), 5)).range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(0), YOffset.fixed(80)))).spreadHorizontally().repeat(5);
    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> oreMagiciumEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(TestMod.MODID, "ore_magicium_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreMagiciumEnd.getValue(), ORE_MAGICIUM_END);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES, oreMagiciumEnd);
    }
}
