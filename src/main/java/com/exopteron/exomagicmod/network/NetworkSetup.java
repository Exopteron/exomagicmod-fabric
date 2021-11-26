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
package com.exopteron.exomagicmod.network;

import com.exopteron.exomagicmod.network.packet.PacketClientHandshake;
import com.exopteron.exomagicmod.network.packet.PacketSpellRegistry;
import com.exopteron.exomagicmod.network.packet.PacketSwitchSpell;

public class NetworkSetup {
    public static int packetID = 0;
    public static void init() {
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketSwitchSpell.class);
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketSpellRegistry.class);
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketClientHandshake.class);
    }
}
