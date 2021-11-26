package com.exopteron.exomagicmod.items.spells;

import com.exopteron.exomagicmod.entities.EntityIceBall;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;


public class FlintSpell implements IWandSpell {
    @Override
    public int useOnBlock(ItemUsageContext ctx) {
        ctx.getStack().setDamage(ctx.getStack().getDamage() - 1);
        Items.FLINT_AND_STEEL.useOnBlock(ctx);
        return 5;
    }
    @Override
    public void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        player.setOnFireFor(5);
    }
    @Override
    public int getSpellCastDurabilityCost() {
        return 0;
    }
}
