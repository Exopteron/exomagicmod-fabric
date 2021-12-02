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

import com.exopteron.exomagicmod.entities.EntityMagicFireball;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class FireballSpell implements IWandSpell {

    @Override
    public boolean cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        ItemStack i = player.getStackInHand(hand);
        //ItemMagicWand.damageWand(i, 1, player, hand);
        EntityMagicFireball snowball = new EntityMagicFireball(world, player);
        snowball.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(snowball);
        return true;
    }
    @Override
    public int getSpellCooldown() {
        return 15;
    }
    @Override
    public void rebound(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        player.setOnFireFor(15);
        player.damage(DamageSource.MAGIC, 6.0F);
        world.createExplosion(player, player.getTrackedPosition().x, player.getTrackedPosition().y, player.getTrackedPosition().z, 1.5F, false, DestructionType.NONE);
    }
}
