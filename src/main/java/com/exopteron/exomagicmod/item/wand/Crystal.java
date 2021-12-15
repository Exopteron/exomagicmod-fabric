package com.exopteron.exomagicmod.item.wand;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public abstract class Crystal extends Item {
    private String wandName;
    public Crystal(Settings settings, String wandName) {
        super(settings);
        this.wandName = wandName;
    }
    public String getWandName() {
        return this.wandName;
    }
    
}
