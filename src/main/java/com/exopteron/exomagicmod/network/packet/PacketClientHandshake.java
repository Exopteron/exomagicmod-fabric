package com.exopteron.exomagicmod.network.packet;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.IExoPacket;
import com.exopteron.exomagicmod.network.Side;

import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

// perfectly bypassable. Server does not check this is sent, but it's nice for organization from now on
public class PacketClientHandshake implements IExoPacket {
    public String version;
    public PacketClientHandshake() {
        
    }
    public PacketClientHandshake(String version) {
        this.version = version;
    }
    @Override
    public void read(PacketByteBuf buf) {
        this.version = buf.readString();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(this.version);
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        try {
            Version otherVersion = Version.parse(this.version); 
            if (otherVersion.compareTo(TestMod.VERSION) != 0) {
                serverPlayer.networkHandler.disconnect(Text.of("Exo's Magic Mod: Incorrect version \"" + otherVersion.getFriendlyString() + "\", we are on " + TestMod.VERSION.getFriendlyString()));
                return;
            }  
            serverPlayer.networkHandler.sendPacket(ExoNetworkManager.INSTANCE.assemble(PacketSpellRegistry.fromSpellRegistry()));
            TestMod.LOGGER.info("Client joining on version " + otherVersion.getFriendlyString());
        } catch (VersionParsingException e) {
            serverPlayer.networkHandler.disconnect(Text.of("Exo's Magic Mod: Invalid version"));
        }
    }
    
}
