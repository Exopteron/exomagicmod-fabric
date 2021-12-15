package com.exopteron.exomagicmod.item.wand.spell.list;

import com.exopteron.exomagicmod.item.wand.spell.WandSpell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class FireballSpell extends WandSpell {

    @Override
    public void castClient(ItemStack wand, PacketByteBuf outBuf) {

    }

    @Override
    public void castServer(ItemStack wand, ServerPlayerEntity player, PacketByteBuf inBuf) {
        FireballEntity fireball = new FireballEntity(player.world, player, 0, 0, 0, 2);
        fireball.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.5F, 1.0F);
        player.world.spawnEntity(fireball);
    }
    @Override
    public void playSound(PlayerEntity player) {
        player.world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1, 1);
    }
    @Override
    public int manaUsage() {
        return 15;
    }
    
}
