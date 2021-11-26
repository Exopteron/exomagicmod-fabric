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
