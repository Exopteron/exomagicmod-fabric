package com.exopteron.exomagicmod.items.spells;

import com.exopteron.exomagicmod.entities.EntityIceBall;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;


public class PearlSpell implements IWandSpell {
    @Override
    public int cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        EnderPearlEntity snowball = new EnderPearlEntity(world, player);
        snowball.setProperties(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(snowball);
        return 25;
    }
    @Override
    public void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15 * 20, 255));
        player.damage(DamageSource.MAGIC, 6.0F);
    }
    @Override
    public int getSpellDurabilityCost() {
        return 5;
    }
}
