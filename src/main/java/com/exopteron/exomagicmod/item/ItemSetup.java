package com.exopteron.exomagicmod.item;

import com.exopteron.exomagicmod.Reference;
import com.exopteron.exomagicmod.item.wand.MagicWand;
import com.exopteron.exomagicmod.item.wand.MagiciumCrystal;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemSetup {
    public static void setup() {
        ItemEntry.setup();
    }
    public static enum WandEntry {

    }
    public static enum ItemEntry {
        MAGICIUM_CRYSTAL("magicium_crystal", new MagiciumCrystal()),
        MAGICIUM_WAND(new MagicWand(ItemEntry.MAGICIUM_CRYSTAL.getItem()))
        ;
        private Identifier identifier;
        private Item item;
        public <T extends Item> T getItem() {
            try {
                return (T) item;
            } catch (ClassCastException e) {
                return null;
            }
        }
        public Identifier getIdentifier() {
            return this.identifier;
        }
        private ItemEntry(MagicWand wand) {
            this.identifier = new Identifier(Reference.MODID, wand.getCrystal().getWandName());
            this.item = wand;
        }
        private ItemEntry(String name, Item item) {
            this.identifier = new Identifier(Reference.MODID, name);
            this.item = item;
        }
        public static void setup() {
            for (ItemEntry b : ItemEntry.values()) {
                Registry.register(Registry.ITEM, b.getIdentifier(), b.getItem());
            }
        }
    }
}
