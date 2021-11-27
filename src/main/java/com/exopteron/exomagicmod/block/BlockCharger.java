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
package com.exopteron.exomagicmod.block;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.block.entity.BlockEntityCharger;
import com.exopteron.exomagicmod.block.entity.BlockEntitySetup;
import com.exopteron.exomagicmod.items.MagiciumSetup;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class BlockCharger extends BlockWithEntity {

    public BlockCharger(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityCharger(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
        return checkType(type, BlockEntitySetup.CHARGER_BLOCK_ENTITY,
                (world1, pos, state1, be) -> BlockEntityCharger.tick(world1, pos, state1, be));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null) {
            BlockEntityCharger chargerBlock = (BlockEntityCharger) blockEntity;
            if (!chargerBlock.itemStack.isEmpty()) {
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), chargerBlock.itemStack));
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityCharger) {
                ContainerProviderRegistry.INSTANCE.openContainer(
                        new Identifier(TestMod.MODID, "screen_crystal_charger_block"), player,
                        buf -> buf.writeBlockPos(pos));
            }
        }
        return ActionResult.SUCCESS;
        /*
         * // System.out.println("Called"); Class<?> c2 =
         * MagiciumSetup.MAGICIUM_CHARGING_GEM.getClass(); Identifier id1 =
         * Registry.ITEM.getId(MagiciumSetup.MAGICIUM_GEM); Identifier id2 =
         * Registry.ITEM.getId(MagiciumSetup.MAGICIUM_CHARGING_GEM); if (world.isClient)
         * { int selectedSlot = player.getInventory().selectedSlot; ItemStack itemInHand
         * = player.getInventory().getStack(selectedSlot); if
         * (Registry.ITEM.getId(itemInHand.getItem()) == id1 || itemInHand.isEmpty() ||
         * Registry.ITEM.getId(itemInHand.getItem()) == id2) { return
         * ActionResult.SUCCESS; } else { return ActionResult.FAIL; } } //
         * System.out.println("A " + world.isClient); BlockEntity blockEntity =
         * world.getBlockEntity(pos); if (blockEntity != null) { int selectedSlot =
         * player.getInventory().selectedSlot; // System.out.println("B");
         * BlockEntityCharger chargerBlock = (BlockEntityCharger) blockEntity; ItemStack
         * itemInHand = player.getInventory().getStack(selectedSlot); if
         * (!(Registry.ITEM.getId(itemInHand.getItem()) == id1 || itemInHand.isEmpty()
         * || Registry.ITEM.getId(itemInHand.getItem()) == id2)) { return
         * ActionResult.FAIL; } if (itemInHand.getCount() > 1) {
         * itemInHand.setCount(itemInHand.getCount() - 1);
         * player.getInventory().setStack(selectedSlot, itemInHand);
         * player.getInventory().insertStack(chargerBlock.itemStack.copy()); } else {
         * player.getInventory().setStack(selectedSlot, chargerBlock.itemStack.copy());
         * } // System.out.println("Selected slot " + selectedSlot + " itemstack " + //
         * chargerBlock.itemStack.getName().getString()); if (itemInHand.isEmpty()) {
         * chargerBlock.itemStack = ItemStack.EMPTY; } else { chargerBlock.itemStack =
         * getItem(itemInHand.copy()); } return ActionResult.SUCCESS; } return
         * ActionResult.SUCCESS;
         */
    }

    public static ItemStack getItem(ItemStack in) {
        Identifier id1 = Registry.ITEM.getId(MagiciumSetup.MAGICIUM_GEM);
        if (Registry.ITEM.getId(in.getItem()) == id1) {
            return new ItemStack(MagiciumSetup.MAGICIUM_CHARGING_GEM);
        } else {
            return in.copy();
        }
    }

    public static boolean goodItem(ItemStack in) {
        Identifier id1 = Registry.ITEM.getId(MagiciumSetup.MAGICIUM_GEM);
        Identifier id2 = Registry.ITEM.getId(MagiciumSetup.MAGICIUM_CHARGING_GEM);
        if (Registry.ITEM.getId(in.getItem()) == id1 || in.isEmpty()
                || Registry.ITEM.getId(in.getItem()) == id2) {
            return true;
        } else {
            return false;
        }
    }
}
