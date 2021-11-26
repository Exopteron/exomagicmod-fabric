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
package com.exopteron.exomagicmod.block;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.block.entity.BlockEntitySetup;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockSetup {
    public static final Block CHARGER_BLOCK = new BlockCharger(FabricBlockSettings.of(Material.STONE));
    public static void init() {
        BlockEntitySetup.init();
        Registry.register(Registry.BLOCK, new Identifier(TestMod.MODID, "crystal_charger_block"), CHARGER_BLOCK);
    }
}
