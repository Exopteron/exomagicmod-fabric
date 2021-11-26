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
