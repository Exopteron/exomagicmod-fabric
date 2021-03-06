/* 
Copyright (c) 2021 Exopteron 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.exopteron.exomagicmod.callbacks;

import com.exopteron.exomagicmod.commands.CommandsSetup;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.packet.PacketSpellRegistry;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.ServerTask;
import net.minecraft.world.TickScheduler;

public class CallbackSetup {
    public static void init() {
        //                 sender.sendPacket(ExoNetworkManager.INSTANCE.assemble(PacketSpellRegistry.fromSpellRegistry()));
/*         ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            server.send(new ServerTask(server.getTicks() + 5, () -> {
            }));
            //ExoNetworkManager.INSTANCE.sendPacketToClient(PacketSpellRegistry.fromSpellRegistry(), newPlayer);
        }); */
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            CommandsSetup.init(dispatcher);
        });
    }
}
