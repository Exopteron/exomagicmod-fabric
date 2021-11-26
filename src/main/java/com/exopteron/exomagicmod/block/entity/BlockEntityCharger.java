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
package com.exopteron.exomagicmod.block.entity;

import com.exopteron.exomagicmod.block.BlockCharger;
import com.exopteron.exomagicmod.items.ItemChargingMagicium;
import com.exopteron.exomagicmod.items.ItemEnergyCrystal;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockEntityCharger extends BlockEntity implements SidedInventory {
    private int blockChargeDelay;
    public ItemStack itemStack = ItemStack.EMPTY;
    public BlockEntityCharger(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public BlockEntityCharger(BlockPos pos, BlockState state) {
        super(BlockEntitySetup.CHARGER_BLOCK_ENTITY, pos, state);
    }
    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        NbtCompound itemTag = new NbtCompound();
        this.itemStack.writeNbt(itemTag);
        tag.put("HeldItem", itemTag);
        return tag;
    }
    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.itemStack = ItemStack.fromNbt(tag.getCompound("HeldItem"));
    }
    public static void tick(World world, BlockPos pos, BlockState state, BlockEntityCharger be) {
        if (be.blockChargeDelay <= 0) {
            if (be.itemStack.getItem() instanceof ItemChargingMagicium) {
                be.itemStack = ItemChargingMagicium.setChargePercent(be.itemStack, ItemChargingMagicium.getChargePercent(be.itemStack) + 1);
                be.blockChargeDelay = 20;
            }
        } else {
            be.blockChargeDelay -= 1;
        }
    }
    @Override
    public int size() {
        return 1;
    }
    @Override
    public boolean isEmpty() {
        return this.itemStack.isEmpty();
    }
    @Override
    public ItemStack getStack(int var1) {
        return this.itemStack;
    }
    @Override
    public ItemStack removeStack(int var1, int var2) {
        ItemStack copy = this.itemStack.copy();
        this.itemStack = ItemStack.EMPTY;
        return copy;
    }
    @Override
    public ItemStack removeStack(int var1) {
        ItemStack copy = this.itemStack.copy();
        this.itemStack = ItemStack.EMPTY;
        return copy;
    }
    @Override
    public void setStack(int var1, ItemStack stack) {
        this.itemStack = BlockCharger.getItem(stack);
    }
    @Override
    public boolean canPlayerUse(PlayerEntity var1) {
        return true;
    }
    @Override
    public void clear() {
        this.itemStack = ItemStack.EMPTY;
    }
    @Override
    public int[] getAvailableSlots(Direction dir) {
        if (dir == Direction.DOWN || dir == Direction.UP) {
            int[] dirs = new int[1];
            dirs[0] = 1;
            return dirs;
        } else {
            return new int[0];
        }
    }
    @Override
    public boolean canInsert(int var1, ItemStack var2, Direction var3) {
        if (var3 == Direction.UP) {
            if (this.itemStack.isEmpty()) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
    @Override
    public boolean canExtract(int var1, ItemStack var2, Direction var3) {
        if (var3 == Direction.DOWN) {
            if (this.itemStack.getItem() instanceof ItemEnergyCrystal) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
