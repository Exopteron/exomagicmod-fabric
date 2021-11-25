package com.exopteron.exotestmod;

import com.exopteron.exotestmod.items.ItemSetup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExoCreativeTab extends ItemGroup {

    public ExoCreativeTab(int index, String id) {
        super(index, id);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ItemStack createIcon() {
        // TODO Auto-generated method stub
        return new ItemStack(ItemSetup.MAGIC_WAND);
    }
    
}
