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
package com.exopteron.exomagicmod.items.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
public interface IWandSpell {
    default int cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        return 0;
    };

    default void castClient(World world, PlayerEntity player, Hand hand, ItemStack wand) {

    };

    default void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        
    };
    default int getSpellCastDurabilityCost() {
        return 1;
    };
    default int getSpellBlockDurabilityCost() {
        return 1;
    };
    default int useOnBlock(ItemUsageContext ctx) {
        return cast(ctx.getWorld(), ctx.getPlayer(), ctx.getHand(), ctx.getStack());
    };
}
