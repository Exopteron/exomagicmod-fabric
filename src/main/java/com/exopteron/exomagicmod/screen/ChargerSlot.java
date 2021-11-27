package com.exopteron.exomagicmod.screen;

import com.exopteron.exomagicmod.block.BlockCharger;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ChargerSlot extends Slot {

    public ChargerSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    @Override
    public boolean canInsert(ItemStack stack) {
        return BlockCharger.goodItem(stack);
    }
}
