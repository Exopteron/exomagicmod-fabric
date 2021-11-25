package com.exopteron.exotestmod.items.spells;

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
    public int cast(World world, PlayerEntity player, Hand hand, ItemStack wand) {
        PotionEntity potion = new PotionEntity(world, player);
        potion.setItem(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.POISON));
        potion.setProperties(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(potion);
        return 25;
    }
}