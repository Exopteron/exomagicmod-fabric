package com.exopteron.exomagicmod.client.gui;

import java.util.ArrayList;
import java.util.List;

import io.github.cottonmc.cotton.gui.widget.WPanel;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class OverlayManager {
    public static final OverlayManager INSTANCE = new OverlayManager();
    private List<OverlayGuiCotton> overlays = new ArrayList<>();
    public void setup() {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            for (OverlayGuiCotton overlay : overlays) {
                if (overlay.shouldDraw()) {
                    MinecraftClient mc = MinecraftClient.getInstance();
                    WPanel rootPanel = overlay.getRootPanel();
                    overlay.tick();
                    rootPanel.paint(matrixStack, rootPanel.getX(), rootPanel.getY(), (int) mc.mouse.getX(), (int) mc.mouse.getY());
                }
            }
        }); 
    }
    public void registerOverlay(OverlayGuiCotton overlay) {
        this.overlays.add(overlay);
    }
}
