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
package com.exopteron.exomagicmod.entities;

import com.exopteron.exomagicmod.TestMod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntitySetup {
    public static final EntityType<EntityMagicFireball> magicFireball = EntityType.Builder.<EntityMagicFireball>create(EntityMagicFireball::new, SpawnGroup.MISC).build("magic_ball");
    
    public static final EntityType<EntityIceBall> iceBall = EntityType.Builder.<EntityIceBall>create(EntityIceBall::new, SpawnGroup.MISC).build("ice_ball");

    public static final EntityType<EntityThrownTorch> thrownTorch = EntityType.Builder.<EntityThrownTorch>create(EntityThrownTorch::new, SpawnGroup.MISC).build("thrown_torch");
    public static void init() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(TestMod.MODID, "ice_ball"), iceBall);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(TestMod.MODID, "magic_ball"), magicFireball);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(TestMod.MODID, "thrown_torch"), thrownTorch);
    }
}
