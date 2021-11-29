package com.exopteron.exomagicmod.items.spells;

import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class MagicBoltSpell implements IWandSpell {
    @Override
    public boolean cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        HitResult hitResult = player.raycast(32, 1.0F, false);
        if (hitResult == null) {
            return false;
        }
        if (hitResult.getType() == Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            world.createExplosion(null, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), 1.0F, DestructionType.NONE);
        } else if (hitResult.getType() == Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            world.createExplosion(null, entityHitResult.getPos().getX(), entityHitResult.getPos().getY(), entityHitResult.getPos().getZ(), 1.0F, DestructionType.NONE);
        } else {
            wand.setDamage(wand.getDamage() - 1);
        }
        return true;
    }
    @Override
    public int getSpellCooldown() {
        return 15;
    }
    @Override
    public void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        IWandSpell.super.rebound(world, player, hand, wand);
    }
}
