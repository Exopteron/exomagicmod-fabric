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
