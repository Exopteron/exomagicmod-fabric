package com.exopteron.exomagicmod.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells.SpellRegistry;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

import net.fabricmc.loader.api.FabricLoader;

public class SpellConfig {
    public static SpellConfig INSTANCE = new SpellConfig();
    public SpellConfigEntries entries;
    public SpellConfigEntry getEntry(String spellID) {
        return this.entries.entries.get(spellID);
    }
    public static void reload() {
        TestMod.LOGGER.info("Reloading spell configurations");
        INSTANCE = new SpellConfig();
    }
    public SpellConfig() {
        File configFile = getConfigFile();
        if (configFile.exists()) {
            this.entries = new Toml().read(configFile).to(SpellConfigEntries.class);
        } else {
            this.entries = SpellConfigEntries.fromRegistry();
            TestMod.LOGGER.info("Spell config does not exist, creating");
            saveConfig();
        }
    }
    private File getConfigFile() {
        return Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "emm_config.toml").toFile();
    }
    public void saveConfig() {
        TomlWriter toml = new TomlWriter();
        try {
            toml.write(this.entries, getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
            TestMod.LOGGER.error("Error saving configuration!");
        }
    }
}
