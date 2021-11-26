package com.exopteron.exomagicmod.block;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.block.entity.BlockEntityCharger;
import com.exopteron.exomagicmod.block.entity.BlockEntitySetup;
import com.exopteron.exomagicmod.items.MagiciumSetup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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
        return checkType(type, BlockEntitySetup.CHARGER_BLOCK_ENTITY, (world1, pos, state1, be) -> BlockEntityCharger.tick(world1, pos, state1, be));
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        //System.out.println("Called");
        Class<?> c2 = MagiciumSetup.MAGICIUM_CHARGING_GEM.getClass();
        Identifier id1 = Registry.ITEM.getId(MagiciumSetup.MAGICIUM_GEM);
        Identifier id2 = Registry.ITEM.getId(MagiciumSetup.MAGICIUM_CHARGING_GEM);
        if (world.isClient) {
            int selectedSlot = player.getInventory().selectedSlot;
            ItemStack itemInHand = player.getInventory().getStack(selectedSlot);
            if (Registry.ITEM.getId(itemInHand.getItem()) == id1 || itemInHand.isEmpty() || Registry.ITEM.getId(itemInHand.getItem()) == id2) {
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.FAIL;
            }
        }
        //System.out.println("A " + world.isClient);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null) {
            int selectedSlot = player.getInventory().selectedSlot;
            //System.out.println("B");
            BlockEntityCharger chargerBlock = (BlockEntityCharger) blockEntity;
            ItemStack itemInHand = player.getInventory().getStack(selectedSlot);
            if (!(Registry.ITEM.getId(itemInHand.getItem()) == id1 || itemInHand.isEmpty() || Registry.ITEM.getId(itemInHand.getItem()) == id2)) {
                return ActionResult.FAIL;
            }
            if (itemInHand.getCount() > 1) {
                itemInHand.setCount(itemInHand.getCount() - 1);
                player.getInventory().setStack(selectedSlot, itemInHand);
                player.getInventory().insertStack(chargerBlock.itemStack.copy());
            } else {
                player.getInventory().setStack(selectedSlot, chargerBlock.itemStack.copy());
            }
            //System.out.println("Selected slot " + selectedSlot + " itemstack " + chargerBlock.itemStack.getName().getString());
            if (itemInHand.isEmpty()) {
                chargerBlock.itemStack = ItemStack.EMPTY;
            } else {
                chargerBlock.itemStack = getItem(itemInHand.copy());
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.SUCCESS;
    }
    public static ItemStack getItem(ItemStack in) {
        Identifier id1 = Registry.ITEM.getId(MagiciumSetup.MAGICIUM_GEM);
        if (Registry.ITEM.getId(in.getItem()) == id1) {
            return new ItemStack(MagiciumSetup.MAGICIUM_CHARGING_GEM);
        } else {
            return in.copy();
        }
    }
}
