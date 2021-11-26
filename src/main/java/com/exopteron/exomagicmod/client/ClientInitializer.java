package com.exopteron.exomagicmod.client;

import com.exopteron.exomagicmod.callbacks.CallbackSetupClient;
import com.exopteron.exomagicmod.client.entities.RendererSetup;
import com.exopteron.exomagicmod.client.gui.GuiSetup;
import com.exopteron.exomagicmod.client.keybind.KeybindSetup;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.IExoPacket;
import com.exopteron.exomagicmod.network.Side;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GuiSetup.init();
        KeybindSetup.init();
        RendererSetup.init();
        CallbackSetupClient.init();
        registerClientNetHandler();
    }

    public void registerClientNetHandler() {
        ClientPlayNetworking.registerGlobalReceiver(ExoNetworkManager.INSTANCE.channel, (client, handler, buf, responseSender) -> {
            int packetID = buf.readVarInt();
            IExoPacket packet = ExoNetworkManager.INSTANCE.idToPackets.get(packetID);
            if (packet != null) {
                packet.read(buf);
            }
            client.execute(() -> {
                if (packet != null) {
                    packet.handle(client.player, Side.LOGICAL_CLIENT);
                }
            });
        });
    }
}
