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
package com.exopteron.exomagicmod.screen;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.block.BlockSetup;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.GeneratorType.ScreenProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class ScreenSetup {
    public static ScreenHandlerType<ScreenHandlerCharger> CHARGERSCREEN;
    public static void init() {
        System.out.println("CAlled ser");
        CHARGERSCREEN = ScreenHandlerRegistry.<ScreenHandlerCharger>registerExtended(new Identifier(TestMod.MODID, "screen_crystal_charger_block"), (syncId, inventory, buf) -> {
            final World world = inventory.player.world;
            world.getBlockState(new BlockPos(0, 0, 0)).getBlock();
            final BlockPos pos = buf.readBlockPos();
            return (ScreenHandlerCharger) world.getBlockState(pos).createScreenHandlerFactory(inventory.player.world, pos).createMenu(syncId, inventory, inventory.player);
        });
        ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier(TestMod.MODID, "screen_crystal_charger_block"), (syncId, identifier, player, buf) -> {
            final World world = player.world;
            world.getBlockState(new BlockPos(0, 0, 0)).getBlock();
            final BlockPos pos = buf.readBlockPos();
            return (ScreenHandlerCharger) world.getBlockState(pos).createScreenHandlerFactory(player.world, pos).createMenu(syncId, player.getInventory(), player);
        });
        System.out.println("registered!");
    }
    public static void initClient() {
        System.out.println("CAlled clin");
        ScreenRegistry.<ScreenHandlerCharger, ScreenCharger>register(CHARGERSCREEN, ScreenCharger::new);
        ScreenProviderRegistry.INSTANCE.<ScreenHandlerCharger>registerFactory(new Identifier(TestMod.MODID, "screen_crystal_charger_block"), (container) -> new ScreenCharger(container, MinecraftClient.getInstance().player.getInventory(), Text.of("Scuffed Charger")));
    }
}
