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
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PoisonCloudSpell implements IWandSpell {
    @Override
    public boolean cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        PotionEntity potion = new PotionEntity(world, player);
        potion.setItem(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.POISON));
        potion.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(potion);
        return true;
    }
    @Override
    public int getSpellCooldown() {
        return 25;
    }
}