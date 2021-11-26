package com.exopteron.exomagicmod.client.gui;

import com.exopteron.exomagicmod.TestMod;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class GuiSetup {
    public static void init() {
        HudRenderCallback.EVENT.register(new GuiMagicWand());
    }
}
