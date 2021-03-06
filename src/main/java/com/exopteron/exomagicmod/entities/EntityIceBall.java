/* 
Copyright (c) 2021 Exopteron 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.exopteron.exomagicmod.entities;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class EntityIceBall extends ThrownItemEntity {
    public EntityIceBall(EntityType<EntityIceBall> type, World world) {
        super(type, world);
    }

    public EntityIceBall(World world, LivingEntity entity) {
        super(EntityType.SNOWBALL, entity, world);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Item getDefaultItem() {
        // TODO Auto-generated method stub
        return Items.SNOWBALL;
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) entity;
            e.setFrozenTicks(15 * 20);
            //e.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15 * 20, 255));
            entity.damage(DamageSource.MAGIC, 6.0F);
        }
        //this.world.getBlockState(new BlockPos(0, 0, 0)).getBlock().toString();
        super.onEntityHit(hitResult);
    }

    @Override
    protected void onBlockHit(BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
        this.hitBlock(pos);
        ArrayList<BlockPos> allPositions = new ArrayList<BlockPos>();
        allPositions.add(pos.north());
        allPositions.add(pos.south());
        allPositions.add(pos.east());
        allPositions.add(pos.west());
        for (BlockPos p : allPositions) {
            this.hitBlock(this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, p));
        }
        // this.kill();
        super.onBlockHit(hitResult);
    }

    private void hitBlock(BlockPos pos) {
        if (this.world.getBlockState(pos).isOf(Blocks.WATER)) {
            this.world.setBlockState(pos, Blocks.ICE.getDefaultState());
        } else if (this.world.getBlockState(pos).canBucketPlace(Fluids.EMPTY)
                && !(this.world.getBlockState(pos.down()).getBlock() instanceof FluidBlock)) {
            this.world.setBlockState(pos, Blocks.SNOW.getDefaultState());
        }
    }

    @Override
    public void tick() {

        BlockPos pos = this.getBlockPos();
        super.tick();
        if (this.world.getBlockState(pos).isOf(Blocks.WATER)) {
            this.world.setBlockState(pos, Blocks.ICE.getDefaultState());
        }
    }
}
