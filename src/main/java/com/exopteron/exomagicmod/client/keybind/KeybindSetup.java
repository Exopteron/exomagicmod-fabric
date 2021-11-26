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
package com.exopteron.exomagicmod.client.keybind;

import com.exopteron.exomagicmod.items.ItemMagicWand;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.packet.PacketSwitchSpell;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeybindSetup {
    private static KeyBinding switchSpell;
    public static KeyBinding showToolTip;
    public static void init() {
        switchSpell = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.exomagicmod.switchspell", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "category.exomagicmod.keybinds"));
        showToolTip = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.exomagicmod.showtooltip", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, "category.exomagicmod.keybinds"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (switchSpell.wasPressed()) {
                if (client.player.getInventory().getStack(client.player.getInventory().selectedSlot).getItem() instanceof ItemMagicWand) {
                    ExoNetworkManager.INSTANCE.sendPacketToServer(new PacketSwitchSpell());
                }
            }            
        });
    }
}
