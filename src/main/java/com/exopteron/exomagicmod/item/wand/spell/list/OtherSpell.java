package com.exopteron.exomagicmod.item.wand.spell.list;

import com.exopteron.exomagicmod.item.wand.spell.WandSpell;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class OtherSpell extends WandSpell {

    @Override
    public void castClient(ItemStack wand, PacketByteBuf outBuf) {

    }

    @Override
    public void castServer(ItemStack wand, ServerPlayerEntity player, PacketByteBuf inBuf) {

    }

    @Override
    public int manaUsage() {
        return 15;
    }
    
}
