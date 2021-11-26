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
