package com.exopteron.exomagicmod.item.wand.spell.list;

import com.exopteron.exomagicmod.item.wand.spell.WandSpell;

import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ElytraSpell extends WandSpell {

    @Override
    public void castClient(ItemStack wand, PacketByteBuf outBuf) {

    }

    @Override
    public void castServer(ItemStack wand, ServerPlayerEntity player, PacketByteBuf inBuf) {
        if (player.isFallFlying()) {
            ItemStack itemStack = Items.FIREWORK_ROCKET.getDefaultStack();
            NbtCompound tag = itemStack.getOrCreateNbt();
            NbtCompound firework = new NbtCompound();
            firework.putByte("Flight", (byte) 3);
            tag.put("Fireworks", firework);
            itemStack.setNbt(tag);
            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(player.world, itemStack, player);
            player.world.spawnEntity(fireworkRocketEntity);
        }
    }

    @Override
    public int manaUsage() {
        return 65;
    }
    @Override
    public int cooldownTicks() {
        return 120;
    }
}
