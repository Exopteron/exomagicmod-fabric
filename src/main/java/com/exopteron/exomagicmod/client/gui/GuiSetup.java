package com.exopteron.exomagicmod.client.gui;

import io.github.cottonmc.cotton.gui.widget.WPanel;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class GuiSetup {
    public static void setup() {
        OverlayManager.INSTANCE.setup();
        OverlayManager.INSTANCE.registerOverlay(new WandOverlay());
    } 
}
