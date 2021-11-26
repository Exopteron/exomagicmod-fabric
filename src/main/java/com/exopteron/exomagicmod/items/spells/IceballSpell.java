package com.exopteron.exomagicmod.items.spells;

import com.exopteron.exomagicmod.entities.EntityIceBall;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;


public class IceballSpell implements IWandSpell {
    @Override
    public int cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        ItemStack i = player.getStackInHand(hand);
        //ItemMagicWand.damageWand(i, 1, player, hand);
        EntityIceBall snowball = new EntityIceBall(world, player);
        snowball.setProperties(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(snowball);
        return 5;
    }
    @Override
    public void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15 * 20, 255));
        player.damage(DamageSource.MAGIC, 6.0F);
    }
}
