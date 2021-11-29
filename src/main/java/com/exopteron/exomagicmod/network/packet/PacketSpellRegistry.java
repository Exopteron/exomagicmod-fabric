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
package com.exopteron.exomagicmod.network.packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.items.spells.IWandSpell;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells.SpellRegistry;
import com.exopteron.exomagicmod.network.IExoPacket;
import com.exopteron.exomagicmod.network.Side;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
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
