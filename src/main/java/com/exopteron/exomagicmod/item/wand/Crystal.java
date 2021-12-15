package com.exopteron.exomagicmod.item.wand;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public abstract class Crystal extends Item {
    private String wandName;
    private int maxMana;
    private int manaGenTicks = 5;
    public Crystal(Settings settings, String wandName, int maxMana) {
        super(settings);
        this.wandName = wandName;
        this.maxMana = maxMana;
    }
    public Crystal setManaGenTicks(int manaGenTicks) {
        this.manaGenTicks = manaGenTicks;
        return this;
    }
    public String getWandName() {
        return this.wandName;
    }
    public int getMaxMana() {
        return this.maxMana;
    }
    public int getManaGenTicks() {
        return this.manaGenTicks;
    }
}
