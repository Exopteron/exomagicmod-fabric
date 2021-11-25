package com.exopteron.exotestmod.network;

import java.util.HashMap;

import com.exopteron.exotestmod.TestMod;

import io.netty.buffer.ByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import oshi.util.tuples.Pair;

public class ExoNetworkManager {
    public static ExoNetworkManager INSTANCE = new ExoNetworkManager(new Identifier(TestMod.MODID, "main"));
    public HashMap<Integer, IExoPacket> idToPackets;
    public HashMap<Class<? extends IExoPacket>, Integer> packetToId;
    public Identifier channel;
    public ExoNetworkManager(Identifier channel) {
        this.idToPackets = new HashMap<Integer, IExoPacket>();
        this.packetToId = new HashMap<Class<? extends IExoPacket>, Integer>();
        this.channel = channel;
        this.registerServerHandler();
    }
    public void registerPacket(int id, Class<? extends IExoPacket> packet) {
        try {
            this.packetToId.put(packet, id);
            this.idToPackets.put(id, packet.getConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Packet<?> assemble(IExoPacket packet) {
        PacketByteBuf buf = PacketByteBufs.create();
        Integer packetID = this.packetToId.get(packet.getClass());
        if (packetID != null) {
            buf.writeVarInt(packetID);
            packet.write(buf);
            return ServerPlayNetworking.createS2CPacket(this.channel, buf);
        }
        return null;
    }
    public void sendPacketToClient(IExoPacket packet, ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        Integer packetID = this.packetToId.get(packet.getClass());
        if (packetID != null) {
            buf.writeVarInt(packetID);
            packet.write(buf);
            ServerPlayNetworking.send(player, this.channel, buf);
        }
    }
    @Environment(EnvType.CLIENT)
    public void sendPacketToServer(IExoPacket packet) {
        PacketByteBuf buf = PacketByteBufs.create();
        Integer packetID = this.packetToId.get(packet.getClass());
        if (packetID != null) {
            buf.writeVarInt(packetID);
            packet.write(buf);
            ClientPlayNetworking.send(this.channel, buf);
        }
    }
    private void registerServerHandler() {
        ServerPlayNetworking.registerGlobalReceiver(this.channel, (server, player, handler, buf, responseSender) -> {
            int packetID = buf.readVarInt();
            IExoPacket packet = this.idToPackets.get(packetID);
            if (packet != null) {
                packet.read(buf);
            }
            server.execute(() -> {
                if (packet != null) {
                    packet.handle(player, Side.LOGICAL_SERVER);
                }
            });
        });
    }
}
