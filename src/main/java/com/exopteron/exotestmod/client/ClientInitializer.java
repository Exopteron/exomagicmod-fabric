package com.exopteron.exotestmod.client;

import com.exopteron.exotestmod.client.entities.RendererSetup;
import com.exopteron.exotestmod.client.gui.GuiSetup;
import com.exopteron.exotestmod.client.keybind.KeybindSetup;
import com.exopteron.exotestmod.network.ExoNetworkManager;
import com.exopteron.exotestmod.network.IExoPacket;
import com.exopteron.exotestmod.network.Side;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GuiSetup.init();
        KeybindSetup.init();
        RendererSetup.init();
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
