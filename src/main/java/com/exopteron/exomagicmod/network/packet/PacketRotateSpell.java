package com.exopteron.exomagicmod.network.packet;

import com.exopteron.exoepiclib.network.ExoPacket;
import com.exopteron.exoepiclib.network.Side;
import com.exopteron.exomagicmod.item.wand.MagicWand;
import com.exopteron.exomagicmod.item.wand.spell.SpellManager;
import com.exopteron.exomagicmod.item.wand.spell.SpellManager.Spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;

public class PacketRotateSpell extends ExoPacket {
    public PacketRotateSpell() {
        
    }
    private Hand hand;
    public boolean up;
    public PacketRotateSpell(boolean up, Hand hand) {
        this.up = up;
        this.hand = hand;
    }
    @Override
    public void handle(PlayerEntity player, Side arg1) {
        if (arg1 == Side.LOGICAL_SERVER) {
            ItemStack hand = player.getStackInHand(this.hand);
            if (hand.getItem() instanceof MagicWand magicWand) {
                Spells spell = Spells.getSpell(hand);
                if (this.up) {
                    spell.offset(1).setSpell(hand);
                } else {
                    spell.offset(-1).setSpell(hand);
                }
            }
        }
    }

    @Override
    public void read(PacketByteBuf arg0) {
        this.up = arg0.readBoolean();
        this.hand = Hand.values()[arg0.readInt()];
    }

    @Override
    public void write(PacketByteBuf arg0) {
        arg0.writeBoolean(this.up);
        int handValue = 0;
        int i = 0;
        for (Hand h : Hand.values()) {
            if (h == this.hand) {
                handValue = i;
                break;
            }
            i++;
        }
        arg0.writeInt(handValue);
    }
}
