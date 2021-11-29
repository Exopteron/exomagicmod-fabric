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

import com.exopteron.exomagicmod.entities.EntityIceBall;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;


public class PearlSpell implements IWandSpell {
    @Override
    public boolean cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        EnderPearlEntity snowball = new EnderPearlEntity(world, player);
        snowball.setProperties(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(snowball);
        return true;
    }
    @Override
    public int getSpellCooldown() {
        return 25;
    }
    @Override
    public void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15 * 20, 255));
        player.damage(DamageSource.MAGIC, 6.0F);
    }
    @Override
    public int getSpellCastDurabilityCost() {
        return 5;
    }
}
