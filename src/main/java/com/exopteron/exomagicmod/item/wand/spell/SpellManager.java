package com.exopteron.exomagicmod.item.wand.spell;

import com.exopteron.exomagicmod.item.wand.MagicWand;
import com.exopteron.exomagicmod.item.wand.spell.list.FireballSpell;
import com.exopteron.exomagicmod.item.wand.spell.list.ElytraSpell;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class SpellManager {
    public static enum Spells {
        FIREBALL(new FireballSpell()),
        OTHERSPELL(new ElytraSpell())
        ;
        private WandSpell spell;
        private Spells(WandSpell spell) {
            this.spell = spell;
        }
        public <T extends WandSpell> T getSpell() {
            try {
                return (T) spell;
            } catch (ClassCastException e) {
                return null;
            }
        }
        public static Spells rotate(Spells in) {
            return Spells.offset(in, 1);
        }
        public static Spells offset(Spells in, int offset) {
            Spells[] values = Spells.values();
            int i = 0;
            for (Spells s : values) {
                if (s == in) {
                    return values[Math.floorMod((i + offset), values.length)]; 
                }
                i++;
            }
            return null;
        }
        public static Spells getSpell(String in) {
            try {
                return Spells.valueOf(in);
            } catch (Exception e) {
                return null;
            }
        }
        public Spells offset(int offset) {
            return Spells.offset(this, offset);
        }
        public static Spells getSpell(ItemStack in) {
            if (!(in.getItem() instanceof MagicWand)) {
                return null;
            }
            NbtCompound tag = in.getOrCreateNbt();
            String spell = tag.getString("SelectedWandSpell");
            Spells spellEnum = Spells.getSpell(spell);
            if (spellEnum == null) {
                spellEnum = Spells.FIREBALL;
                spellEnum.setSpell(in);
            }
            return spellEnum;
        }
        public void setSpell(ItemStack in) {
            if (!(in.getItem() instanceof MagicWand)) {
                return;
            }
            NbtCompound tag = in.getOrCreateNbt();
            tag.putString("SelectedWandSpell", this.name());
            in.setNbt(tag);
        }
    }
}
