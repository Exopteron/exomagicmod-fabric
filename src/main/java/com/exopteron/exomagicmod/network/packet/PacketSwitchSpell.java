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

import com.exopteron.exomagicmod.items.ItemMagicWand;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells.SpellRegistry;
import com.exopteron.exomagicmod.network.IExoPacket;
import com.exopteron.exomagicmod.network.Side;

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
