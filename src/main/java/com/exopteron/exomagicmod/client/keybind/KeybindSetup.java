package com.exopteron.exomagicmod.client.keybind;

import com.exopteron.exomagicmod.item.wand.MagicWand;
import com.exopteron.exomagicmod.network.NetworkSetup;
import com.exopteron.exomagicmod.network.packet.PacketRotateSpell;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

public class KeybindSetup {
    private static KeyBinding upSpell = new KeyBinding("key.exomagicmod.upspell", GLFW.GLFW_KEY_UP, "Exo Magic Mod");
    private static KeyBinding downSpell = new KeyBinding("key.exomagicmod.downSpell", GLFW.GLFW_KEY_DOWN, "Exo Magic Mod");
    public static void setup() {
        upSpell = KeyBindingHelper.registerKeyBinding(upSpell);
        downSpell = KeyBindingHelper.registerKeyBinding(downSpell);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (upSpell.wasPressed()) {
                if (client.player.getInventory().getStack(client.player.getInventory().selectedSlot).getItem() instanceof MagicWand) {
                    NetworkSetup.INSTANCE.sendPacketToServer(new PacketRotateSpell(true, client.player.getActiveHand()));
                }   
            }
            while (downSpell.wasPressed()) {
                if (client.player.getInventory().getStack(client.player.getInventory().selectedSlot).getItem() instanceof MagicWand) {
                    NetworkSetup.INSTANCE.sendPacketToServer(new PacketRotateSpell(false, client.player.getActiveHand()));
                }   
            }
        });
    }
}
