package com.exopteron.exomagicmod.client.gui;

import io.github.cottonmc.cotton.gui.widget.WPanel;

public abstract class OverlayGuiCotton {
    private WPanel root;
    protected void setRootPanel(WPanel root) {
        this.root = root;
    }
    public WPanel getRootPanel() {
        return this.root;
    }
    public abstract void tick();
    public boolean shouldDraw() {
        return true;
    }
}
