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
import com.exopteron.exomagicmod.block.BlockSetup;
import com.exopteron.exomagicmod.items.tooltip.TooltippableBlockItem;
import com.exopteron.exomagicmod.items.tooltip.TooltippableItem;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemSetup {
    public static final Item MAGIC_WAND = new ItemMagicWand(new Item.Settings().maxDamage(150).group(TestMod.CREATIVE_TAB));
    public static final Item CHARGER_BLOCK_ITEM = new TooltippableBlockItem(BlockSetup.CHARGER_BLOCK, new Item.Settings().group(TestMod.CREATIVE_TAB), Text.of("Charge all the crystals!"));
    public static Item ENERGY_CRYSTAL;
    public static Item NETHERITE_STICK;
    public static void run(TestMod main) {
        MagiciumSetup.init();
        Settings settings = new Item.Settings();
        settings.maxCount(1);
        settings.group(TestMod.CREATIVE_TAB);
        ENERGY_CRYSTAL = new ItemEnergyCrystal(settings);
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "energy_crystal"), ENERGY_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "magic_wand"), MAGIC_WAND);
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "crystal_charger_block"), CHARGER_BLOCK_ITEM);
        settings = new Item.Settings();
        settings.fireproof();
        settings.group(TestMod.CREATIVE_TAB);
        NETHERITE_STICK = new TooltippableItem(settings, Text.of("A stick. But out of netherite."));
        Registry.register(Registry.ITEM, new Identifier(TestMod.MODID, "netherite_stick"), NETHERITE_STICK);
    }   
}
