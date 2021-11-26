package com.exopteron.exomagicmod.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class EntityMagicFireball extends ThrownItemEntity {
    public EntityMagicFireball(EntityType<EntityMagicFireball> type, World world) {
        super(type, world);
    }
    public EntityMagicFireball(World world, LivingEntity entity) {
        super(EntityType.SNOWBALL, entity, world);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected Item getDefaultItem() {
        // TODO Auto-generated method stub
        return Items.SNOWBALL;
    }
    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        entity.setOnFireFor(15);
        entity.damage(DamageSource.MAGIC, 6.0F);
        this.world.createExplosion(entity, entity.getTrackedPosition().x, entity.getTrackedPosition().y, entity.getTrackedPosition().z, 1.5F, false, DestructionType.NONE);
        this.kill();
        super.onEntityHit(hitResult);
    }
    @Override
    protected void onBlockHit(BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
        if (this.world.getBlockState(pos).canBucketPlace(Fluids.EMPTY)) {
            this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }
        this.world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, DestructionType.NONE);
        this.kill();
        super.onBlockHit(hitResult);
    }
}
