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
package com.exopteron.exomagicmod.block.entity;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.block.BlockSetup;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntitySetup {
    public static final BlockEntityType<BlockEntityCharger> CHARGER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(BlockEntityCharger::new, BlockSetup.CHARGER_BLOCK).build();
    public static void init() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(TestMod.MODID, "crystal_charger_block_entity"), CHARGER_BLOCK_ENTITY);
    }
}
