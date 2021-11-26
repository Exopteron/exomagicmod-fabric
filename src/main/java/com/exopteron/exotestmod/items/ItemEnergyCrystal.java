package com.exopteron.exotestmod.items;

import java.util.List;

import com.exopteron.exotestmod.items.tooltip.TooltippableItem;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ItemEnergyCrystal extends TooltippableItem {

    public ItemEnergyCrystal(Settings settings) {
        super(settings);
        //TODO Auto-generated constructor stub
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Crystal Damage: " + ItemMagicWand.getCrystalDamage(stack)).copy().formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
    public static void setCrystalDamage(ItemStack crystal, int damage) {
        NbtCompound tag = crystal.getOrCreateNbt();
        tag.putInt("CrystalDamage", damage);
    }
    @Override
    public Text extraTooltipData() {
        return Text.of("Doesn't run on electricity!").copy().formatted(Formatting.GRAY);
    }
}
