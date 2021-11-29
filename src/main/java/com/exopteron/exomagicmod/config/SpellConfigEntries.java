package com.exopteron.exomagicmod.config;

import java.util.HashMap;
import java.util.Map;

import com.exopteron.exomagicmod.items.spells.IWandSpell;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells.SpellRegistry;

import oshi.util.tuples.Pair;

public class SpellConfigEntries {
    public HashMap<String, SpellConfigEntry> entries;
    public SpellConfigEntries(HashMap<String ,SpellConfigEntry> entries) {
        this.entries = entries;
    }
    public static SpellConfigEntries fromRegistry() {
        HashMap<String, SpellConfigEntry> entries = new HashMap<>();
        for (Map.Entry<String, Pair<IWandSpell, String>> entry : SpellRegistry.INSTANCE.spellRegistry.entrySet()) {
            entries.put(entry.getKey(), new SpellConfigEntry(entry.getKey(), entry.getValue().getB(), entry.getValue().getA().getSpellCooldown(), entry.getValue().getA().getSpellCastDurabilityCost(), entry.getValue().getA().getSpellBlockDurabilityCost()));
        }
        return new SpellConfigEntries(entries);
    }
}
