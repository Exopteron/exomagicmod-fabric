package com.exopteron.exotestmod.client.keybind;

import com.exopteron.exotestmod.items.ItemMagicWand;
import com.exopteron.exotestmod.network.ExoNetworkManager;
import com.exopteron.exotestmod.network.packet.PacketSwitchSpell;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeybindSetup {
    private static KeyBinding switchSpell;
    public static KeyBinding showToolTip;
    public static void init() {
        switchSpell = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.exotestmod.switchspell", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "category.exotestmod.keybinds"));
        showToolTip = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.exotestmod.showtooltip", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, "category.exotestmod.keybinds"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (switchSpell.wasPressed()) {
                if (client.player.getInventory().getStack(client.player.getInventory().selectedSlot).getItem() instanceof ItemMagicWand) {
                    ExoNetworkManager.INSTANCE.sendPacketToServer(new PacketSwitchSpell());
                }
            }            
        });
    }
}
