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

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.IExoPacket;
import com.exopteron.exomagicmod.network.Side;

import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

// perfectly bypassable. Server does not check this is sent, but it's nice for organization from now on
public class PacketClientHandshake implements IExoPacket {
    public String version;

    public PacketClientHandshake() {

    }

    public PacketClientHandshake(String version) {
        this.version = version;
    }

    @Override
    public void read(PacketByteBuf buf) {
        this.version = buf.readString();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(this.version);
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        try {
            Version otherVersion = Version.parse(this.version);
            if (otherVersion.compareTo(TestMod.VERSION) != 0) {
                serverPlayer.networkHandler.disconnect(Text.of("Exo's Magic Mod: Incorrect version \""
                        + otherVersion.getFriendlyString() + "\", we are on " + TestMod.VERSION.getFriendlyString()));
                return;
            }
            serverPlayer.networkHandler
                    .sendPacket(ExoNetworkManager.INSTANCE.assemble(PacketSpellRegistry.fromSpellRegistry()));
            TestMod.LOGGER.info("Client joining on version " + otherVersion.getFriendlyString());
        } catch (VersionParsingException e) {
            serverPlayer.networkHandler.disconnect(Text.of("Exo's Magic Mod: Invalid version"));
        }
    }

}
