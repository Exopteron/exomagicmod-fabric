package com.exopteron.exotestmod.network.packet;

import com.exopteron.exotestmod.items.ItemMagicWand;
import com.exopteron.exotestmod.items.spells.MagicWandSpells;
import com.exopteron.exotestmod.items.spells.MagicWandSpells.SpellRegistry;
import com.exopteron.exotestmod.network.IExoPacket;
import com.exopteron.exotestmod.network.Side;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PacketSwitchSpell implements IExoPacket {
    public PacketSwitchSpell() {
        
    }
    @Override
    public void read(PacketByteBuf buf) {
        
    }

    @Override
    public void write(PacketByteBuf buf) {
        
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        if (side == Side.LOGICAL_SERVER) {
            ServerPlayerEntity sender = (ServerPlayerEntity) player;
            ItemStack heldItemStack = sender.getInventory().getStack(sender.getInventory().selectedSlot);
            if (heldItemStack.getItem() instanceof ItemMagicWand) {
                String spell = MagicWandSpells.spellFromItemStack(heldItemStack);
                spell = MagicWandSpells.rotate(spell);
                SpellRegistry.setItemStack(heldItemStack, spell);
            }
        }
    }
    
}
