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
package com.exopteron.exomagicmod.items;

import java.util.ArrayList;
import java.util.List;

import com.exopteron.exomagicmod.items.tooltip.TooltippableItem;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ItemEnergyCrystal extends TooltippableItem {

    public ItemEnergyCrystal(Settings settings) {
        super(settings);
        //TODO Auto-generated constructor stub
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Crystal Damage: " + ItemMagicWand.getCrystalDamage(stack)).copy().formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
    public static void setCrystalDamage(ItemStack crystal, int damage) {
        NbtCompound tag = crystal.getOrCreateNbt();
        tag.putInt("CrystalDamage", damage);
    }
    @Override
    public List<Text> extraTooltipData() {
        List<Text> list = new ArrayList<>();
        list.add(Text.of("Doesn't run on electricity!").copy().formatted(Formatting.GRAY));
        list.add(Text.of("Can be used with a netherite stick to create a magic wand.").copy().formatted(Formatting.GRAY));
        return list;
    }
}
