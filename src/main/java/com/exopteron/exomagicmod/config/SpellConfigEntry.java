package com.exopteron.exomagicmod.config;

import java.util.HashMap;
import java.util.Map;

public class SpellConfigEntry {
    public Map<String, Object> configMap;
    public SpellConfigEntry(String spellID, String spellName, int spellCooldown, int durabilityCostCast, int durabilityCostBlock) {
        this.configMap = new HashMap<String, Object>();
        this.configMap.put("spell_id", (Object) spellID);
        this.configMap.put("spell_name", (Object) spellName);
        this.configMap.put("spell_cooldown", (Object) spellCooldown);
        this.configMap.put("durab_cost_cast", (Object) durabilityCostCast);
        this.configMap.put("durab_cost_block", (Object) durabilityCostBlock);
    }
    public String getSpellID() {
        return (String) this.configMap.get("spell_id");
    }
    public String getSpellName() {
        return (String) this.configMap.get("spell_name");
    }
    public int getSpellCooldown() {
        return ((Double) this.configMap.get("spell_cooldown")).intValue();
    }
    public int getSpellDurabCostCast() {
        return ((Double) this.configMap.get("durab_cost_cast")).intValue();
    }
    public int getSpellDurabCostBlock() {
        return ((Double) this.configMap.get("durab_cost_block")).intValue();
    }
}
