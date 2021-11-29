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
package com.exopteron.exomagicmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.mixin.networking.accessor.MinecraftClientAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import com.exopteron.exomagicmod.block.BlockSetup;
import com.exopteron.exomagicmod.callbacks.CallbackSetup;
import com.exopteron.exomagicmod.config.SpellConfig;
import com.exopteron.exomagicmod.entities.EntitySetup;
import com.exopteron.exomagicmod.items.ItemSetup;
import com.exopteron.exomagicmod.network.NetworkSetup;
import com.exopteron.exomagicmod.recipe.RecipeSetup;
import com.exopteron.exomagicmod.screen.ScreenSetup;
import com.exopteron.exomagicmod.world.OregenSetup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestMod implements ModInitializer {
	public static TestMod INSTANCE;
	public static final String MODID = "exomagicmod";
	public static final ModMetadata METADATA = FabricLoader.getInstance().getModContainer(MODID).get().getMetadata();
	public static final Version VERSION = METADATA.getVersion();
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(new Identifier(MODID, "general"), () -> new ItemStack(ItemSetup.MAGIC_WAND));
	@Override
	public void onInitialize() {
		SpellConfig.INSTANCE.entries.equals(4);
		INSTANCE = this;
		ItemSetup.run(this);
		RecipeSetup.init();
		NetworkSetup.init();
		EntitySetup.init();
		CallbackSetup.init();
		BlockSetup.init();
		OregenSetup.init();
		ScreenSetup.init();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world! Mod version " + VERSION.getFriendlyString());
	}
}
