package com.exopteron.exomagicmod.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public interface IExoPacket {
    public void read(PacketByteBuf buf);
    public void write(PacketByteBuf buf);
    public void handle(PlayerEntity player, Side side);
}
