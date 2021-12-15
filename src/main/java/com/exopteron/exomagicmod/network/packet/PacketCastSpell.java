package com.exopteron.exomagicmod.network.packet;

import com.exopteron.exoepiclib.network.ExoPacket;
import com.exopteron.exoepiclib.network.Side;
import com.exopteron.exomagicmod.item.wand.MagicWand;
import com.exopteron.exomagicmod.item.wand.spell.SpellManager;
import com.exopteron.exomagicmod.item.wand.spell.SpellManager.Spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

public class PacketCastSpell extends ExoPacket {
    public PacketCastSpell() {

    }
    private String spellName;
    private Hand hand;
    private PacketByteBuf buf;
    public PacketCastSpell(String spellName, PacketByteBuf buf, Hand hand) {
        this.spellName = spellName;
        this.buf = buf;
        this.hand = hand;
    }
    @Override
    public void handle(PlayerEntity player, Side side) {
        if (side == Side.LOGICAL_SERVER) {
            Spells spell = SpellManager.Spells.getSpell(this.spellName);
            if (spell != null) {
                ItemStack hand = player.getStackInHand(this.hand);
                if (hand.getItem() instanceof MagicWand magicWand) {
                    if (magicWand.canCastSpell(player, hand, spell.getSpell(), false)) {
                        spell.getSpell().castServer(hand, (ServerPlayerEntity) player, this.buf);
                        spell.getSpell().playSound(player);
                        player.getItemCooldownManager().set(hand.getItem(), spell.getSpell().cooldownTicks());
                    }
                }
            }
        }
    }
    @Override
    public void read(PacketByteBuf arg0) {
        this.spellName = arg0.readString();
        this.hand = Hand.values()[arg0.readInt()];
        this.buf = arg0;
    }
    @Override
    public void write(PacketByteBuf arg0) {
        arg0.writeString(this.spellName);
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
        arg0.writeBytes(this.buf);
    }
}
