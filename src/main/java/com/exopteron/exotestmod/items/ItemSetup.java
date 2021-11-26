package com.exopteron.exotestmod.items;

import com.exopteron.exotestmod.TestMod;
import com.exopteron.exotestmod.items.tooltip.TooltippableItem;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;

public class ItemSetup {
    public static final Item MAGIC_WAND = new ItemMagicWand(new Item.Settings().maxDamage(150).group(TestMod.CREATIVE_TAB));
    public static Item ENERGY_CRYSTAL;
    public static Item NETHERITE_STICK;
    public static void run(TestMod main) {
        MagiciumSetup.init();
        Settings settings = new Item.Settings();
        settings.maxCount(1);
        settings.group(TestMod.CREATIVE_TAB);
        ENERGY_CRYSTAL = new ItemEnergyCrystal(settings);
        Registry.register(Registry.ITEM, "exotestmod:energy_crystal", ENERGY_CRYSTAL);
        Registry.register(Registry.ITEM, "exotestmod:magic_wand", MAGIC_WAND);

        settings = new Item.Settings();
        settings.fireproof();
        settings.group(TestMod.CREATIVE_TAB);
        NETHERITE_STICK = new TooltippableItem(settings).setExtraInfo(Text.of("A stick. But out of netherite.").copy().formatted(Formatting.DARK_GRAY));
        Registry.register(Registry.ITEM, "exotestmod:netherite_stick", NETHERITE_STICK);
    }   
}
