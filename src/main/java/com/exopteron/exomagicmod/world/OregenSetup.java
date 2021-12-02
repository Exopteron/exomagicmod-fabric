/* 
Copyright (c) 2021 Exopteron 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
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
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class OregenSetup {
    private static ConfiguredFeature<?, ?> ORE_MAGICIUM_END = Feature.ORE.configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), MagiciumSetup.MAGICIUM_ORE.getDefaultState(), 5));
    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> oreMagiciumEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(TestMod.MODID, "ore_magicium_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreMagiciumEnd.getValue(), ORE_MAGICIUM_END);
        RegistryKey<PlacedFeature> orePlacedFeature = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(TestMod.MODID, "ore_magicium_end"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, oreMagiciumEnd.getValue(), ORE_MAGICIUM_END.withPlacement(OrePlacedFeatures.modifiersWithCount(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(0), YOffset.aboveBottom(80)))));
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES, orePlacedFeature);
    }
}
