package com.exopteron.exomagicmod.items.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
public interface IWandSpell {
    default int cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        return 0;
    };

    default void castClient(World world, PlayerEntity player, Hand hand, ItemStack wand) {

    };

    default void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        
    };
    default int getSpellCastDurabilityCost() {
        return 1;
    };
    default int getSpellBlockDurabilityCost() {
        return 1;
    };
    default int useOnBlock(ItemUsageContext ctx) {
        return cast(ctx.getWorld(), ctx.getPlayer(), ctx.getHand(), ctx.getStack());
    };
}
