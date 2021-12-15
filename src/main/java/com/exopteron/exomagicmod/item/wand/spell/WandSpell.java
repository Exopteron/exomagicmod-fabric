package com.exopteron.exomagicmod.item.wand.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public abstract class WandSpell {
    public abstract void castClient(ItemStack wand, PacketByteBuf outBuf);
    public abstract void castServer(ItemStack wand, ServerPlayerEntity player, PacketByteBuf inBuf);
    public abstract int manaUsage();
    public int cooldownTicks() {
        return 25;
    }
    public void playSound(PlayerEntity player) {
        player.world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM, SoundCategory.PLAYERS, 1, 1);
    }
}
