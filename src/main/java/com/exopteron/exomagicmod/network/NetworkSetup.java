package com.exopteron.exomagicmod.network;

import com.exopteron.exoepiclib.Reference;
import com.exopteron.exoepiclib.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.packet.PacketCastSpell;
import com.exopteron.exomagicmod.network.packet.PacketRotateSpell;

import net.minecraft.util.Identifier;

public class NetworkSetup {
    public static final ExoNetworkManager INSTANCE = new ExoNetworkManager(new Identifier(Reference.MODID, "main"));
    private static int packetID = 0;
    public static void setup() {
        INSTANCE.registerPacket(packetID++, PacketCastSpell.class);
        INSTANCE.registerPacket(packetID++, PacketRotateSpell.class);
    }
}
