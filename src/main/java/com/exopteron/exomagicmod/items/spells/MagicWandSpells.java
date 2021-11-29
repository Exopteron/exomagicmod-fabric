/* 
Copyright (c) 2021 Exopteron 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.exopteron.exomagicmod.items.spells;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import oshi.util.tuples.Pair;


public class MagicWandSpells {
    public static String rotate(String in) {
        String[] spells = new String[SpellRegistry.INSTANCE.spellRegistry.keySet().size()];
        SpellRegistry.INSTANCE.spellRegistry.keySet().toArray(spells);
        int i = 0;
        int finalInt = 0;
        for (String spell : spells) {
            if (spell == in) {
                finalInt = i;
            }
            i++;
        }
        finalInt += 1;
        finalInt %= spells.length;
        return spells[finalInt];
    } 
    public class SpellRegistry {
        public static SpellRegistry INSTANCE = new MagicWandSpells().new SpellRegistry();
        public HashMap<String, Pair<IWandSpell, String>> spellRegistry;
        public HashMap<String, String> clientSpellRegistry;
        public static String defaultSpell = "FIREBALL";
        public SpellRegistry() {
            this.spellRegistry = new HashMap<String, Pair<IWandSpell, String>>();
            this.clientSpellRegistry = new HashMap<String, String>();
            this.registerSpell("FIREBALL", "Fireball", new FireballSpell());
            this.registerSpell("ICEBALL", "Iceball", new IceballSpell());
            //this.registerSpell("TESTSPELL", new TestSpell());
            this.registerSpell("POISONCLOUD", "Poison Cloud", new PoisonCloudSpell());
            this.registerSpell("THROWNTORCH", "Torch Thrower", new ThrownTorchSpell());
            this.registerSpell("PEARLSPELL", "Ender Pearl", new PearlSpell());
            this.registerSpell("FLINTSTEELSPELL", "Flint and Steel", new FlintSpell());
            this.registerSpell("BOLTSPELL", "Magic Bolt", new MagicBoltSpell());
            this.registerSpell("LIGHTNINGSPELL", "Lightning Bolt", new LightningSpell());
            this.registerSpell("EXPLOSIONSPELL", "Kamikaze", new ExplosionSpell());
            /*             this.registerSpell("FIREBALL", new FireballSpell());
            this.registerSpell("ICEBALL", new IceballSpell());
            this.registerSpell("POISONCLOUD", new PoisonCloudSpell()); */
        }
        public void registerSpell(String spellID, String spellName, IWandSpell spell) {
            this.spellRegistry.put(spellID, new Pair<IWandSpell, String>(spell, spellName));
        }
        public static void setItemStack(ItemStack i, String spell) {
            NbtCompound tag = i.getOrCreateNbt();
            tag.putString("CurrentSpell", spell);
        }
    }
    public static String spellFromItemStack(ItemStack in) {
        NbtCompound tag = in.getOrCreateNbt();
        String s = tag.getString("CurrentSpell");
        //System.out.println("CAlled with " + s);
        if (s == null || s == "") {
            tag.putString("CurrentSpell", SpellRegistry.defaultSpell);
        }
        s = tag.getString("CurrentSpell");
        in.setNbt(tag);
        //System.out.println("S is " + s);
        return s;
    }
}
