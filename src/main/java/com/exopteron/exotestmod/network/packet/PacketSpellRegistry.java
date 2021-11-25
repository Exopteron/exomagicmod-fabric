package com.exopteron.exotestmod.network.packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.exopteron.exotestmod.TestMod;
import com.exopteron.exotestmod.items.spells.IWandSpell;
import com.exopteron.exotestmod.items.spells.MagicWandSpells.SpellRegistry;
import com.exopteron.exotestmod.network.IExoPacket;
import com.exopteron.exotestmod.network.Side;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import oshi.util.tuples.Pair;

public class PacketSpellRegistry implements IExoPacket {
    public HashMap<String, String> spellList;
    public PacketSpellRegistry(HashMap<String, String> spellList) {
        this.spellList = spellList;
    }
    public PacketSpellRegistry() {

    }
    public static PacketSpellRegistry fromSpellRegistry() {
        HashMap<String, String> spellList = new HashMap<String, String>();
        for (Map.Entry<String, Pair<IWandSpell, String>> entry : SpellRegistry.INSTANCE.spellRegistry.entrySet()) {
            spellList.put(entry.getKey(), entry.getValue().getB());
        }
        return new PacketSpellRegistry(spellList);
    }
    @Override
    public void read(PacketByteBuf buf) {
        HashMap<String, String> spells = new HashMap<String, String>();
        int elementCount = buf.readVarInt();
        int i;
        for (i = 0; i < elementCount; i++) {
            spells.put(buf.readString(), buf.readString());
        }
        this.spellList = spells;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(this.spellList.size());
        for (Map.Entry<String, String> spellEntry : this.spellList.entrySet()) {
            buf.writeString(spellEntry.getKey());
            buf.writeString(spellEntry.getValue());
        }
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        // display names later
        SpellRegistry.INSTANCE.clientSpellRegistry = new HashMap<String, String>();
        for (Map.Entry<String, String> spellEntry : this.spellList.entrySet()) {
            SpellRegistry.INSTANCE.clientSpellRegistry.put(spellEntry.getKey(), spellEntry.getValue());
        }
        TestMod.LOGGER.info("[Exo's Magic Mod] Recieved " + SpellRegistry.INSTANCE.clientSpellRegistry.size() + " spells from server");
    }
    
}
