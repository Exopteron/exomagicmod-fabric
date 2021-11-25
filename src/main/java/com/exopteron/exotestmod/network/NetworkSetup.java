package com.exopteron.exotestmod.network;

import com.exopteron.exotestmod.network.packet.PacketSpellRegistry;
import com.exopteron.exotestmod.network.packet.PacketSwitchSpell;

public class NetworkSetup {
    public static int packetID = 0;
    public static void init() {
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketSwitchSpell.class);
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketSpellRegistry.class);
    }
}
