package com.exopteron.exomagicmod.callbacks;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.packet.PacketClientHandshake;

public class CallbackSetupClient {
    public static void init() {
        ClientJoinGameCallback.EVENT.register(() -> {
            ExoNetworkManager.INSTANCE.sendPacketToServer(new PacketClientHandshake(TestMod.VERSION.getFriendlyString()));
        }); 
    }
}
