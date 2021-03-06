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
package com.exopteron.exomagicmod.items;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.items.tooltip.TooltippableBlockItem;
import com.exopteron.exomagicmod.items.tooltip.TooltippableItem;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MagiciumSetup {
    public static Item MAGICIUM_GEM;
    public static Block MAGICIUM_BLOCK;
    public static Item MAGICIUM_BLOCK_ITEM;
    public static Block MAGICIUM_ORE;
    public static Item MAGICIUM_ORE_ITEM;
    public static final Item MAGICIUM_CHARGING_GEM = new ItemChargingMagicium(new Item.Settings().maxCount(1), Text.of("On your way to a shocking experience!"));
    public static void init() {
        Settings settings = new Item.Settings();
        settings.maxCount(64);
        settings.group(TestMod.CREATIVE_TAB);
        MAGICIUM_GEM = new TooltippableItem(settings, Text.of("A gem of pure magicium.")).addExtraInfo(Text.of("Can be put in a Crystal Charger to create an Energy Crystal."));
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "magicium_gem"), MAGICIUM_GEM);
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "magicium_charging"), MAGICIUM_CHARGING_GEM);
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK);
        MAGICIUM_BLOCK = new Block(blockSettings);
        Registry.register(Registry.BLOCK, new Identifier(TestMod.MODID, "magicium_block"), MAGICIUM_BLOCK);
        
        settings = new Item.Settings();
        settings.group(TestMod.CREATIVE_TAB);
        MAGICIUM_BLOCK_ITEM = new TooltippableBlockItem(MAGICIUM_BLOCK, settings, Text.of("A block of pure magicium!"));
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "magicium_block"), MAGICIUM_BLOCK_ITEM);

        blockSettings = FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE);
        MAGICIUM_ORE = new OreBlock(blockSettings);
        Registry.register(Registry.BLOCK, new Identifier(TestMod.MODID, "magicium_ore"), MAGICIUM_ORE);

        settings = new Item.Settings();
        settings.group(TestMod.CREATIVE_TAB);
        MAGICIUM_ORE_ITEM = new TooltippableBlockItem(MAGICIUM_ORE, settings, Text.of("Magicium ore. can't think of anything else to say."));
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "magicium_ore"), MAGICIUM_ORE_ITEM);
    }
}
