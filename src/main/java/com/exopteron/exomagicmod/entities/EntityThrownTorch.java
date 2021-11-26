package com.exopteron.exomagicmod.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class EntityThrownTorch extends ThrownItemEntity {
    public EntityThrownTorch(EntityType<EntityThrownTorch> type, World world) {
        super(type, world);
    }
    public EntityThrownTorch(World world, LivingEntity entity) {
        super(EntitySetup.thrownTorch, entity, world);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected Item getDefaultItem() {
        // TODO Auto-generated method stub
        return Items.TORCH;
    }
    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) entity;
            e.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 15 * 20, 255));
        }
        this.kill();
        super.onEntityHit(hitResult);
    }
    @Override
    protected void onBlockHit(BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
        if (this.world.getBlockState(pos).canBucketPlace(Fluids.EMPTY)) {
            // TODO orient the torch correctly
            this.world.setBlockState(pos, Blocks.TORCH.getDefaultState());
        }
        //this.world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, DestructionType.NONE);
        this.kill();
        super.onBlockHit(hitResult);
    }
}
