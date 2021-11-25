package com.exopteron.exotestmod.client.gui;

import com.exopteron.exotestmod.TestMod;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class GuiSetup {
    public static void init() {
        HudRenderCallback.EVENT.register(new GuiMagicWand());
    }
}
