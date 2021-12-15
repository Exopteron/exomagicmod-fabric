package com.exopteron.exomagicmod.client;

import com.exopteron.exomagicmod.client.gui.GuiSetup;
import com.exopteron.exomagicmod.client.keybind.KeybindSetup;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class ClientSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GuiSetup.setup();
        KeybindSetup.setup();
    }
    
}
