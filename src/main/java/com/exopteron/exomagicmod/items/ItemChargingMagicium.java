package com.exopteron.exomagicmod.items;

import java.util.List;

import com.exopteron.exomagicmod.items.tooltip.TooltippableItem;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ItemChargingMagicium extends TooltippableItem {

    public ItemChargingMagicium(Settings settings, Text info) {
        super(settings, info);
    }
    public static ItemStack setChargePercent(ItemStack in, int chargePercent) {
        if (chargePercent >= 100) {
            in = new ItemStack(ItemSetup.ENERGY_CRYSTAL);
        } else {
            NbtCompound tag = in.getOrCreateNbt();
            tag.putInt("ChargePercent", chargePercent);
            in.setNbt(tag);
        }
        return in;
    }
    public static int getChargePercent(ItemStack in) {
        NbtCompound tag = in.getOrCreateNbt();
        return tag.getInt("ChargePercent");
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Charge percentage: " + getChargePercent(stack)).copy().formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

