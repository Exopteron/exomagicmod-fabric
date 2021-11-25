package com.exopteron.exotestmod.callbacks;

import com.exopteron.exotestmod.network.ExoNetworkManager;
import com.exopteron.exotestmod.network.packet.PacketSpellRegistry;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.ServerTask;
import net.minecraft.world.TickScheduler;

public class CallbackSetup {
    public static void init() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            server.send(new ServerTask(server.getTicks() + 5, () -> {
                sender.sendPacket(ExoNetworkManager.INSTANCE.assemble(PacketSpellRegistry.fromSpellRegistry()));
            }));
            //ExoNetworkManager.INSTANCE.sendPacketToClient(PacketSpellRegistry.fromSpellRegistry(), newPlayer);
        });
    }
}
