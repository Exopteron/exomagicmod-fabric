package com.exopteron.exomagicmod.entities;

import com.exopteron.exomagicmod.TestMod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntitySetup {
    public static final EntityType<EntityMagicFireball> magicFireball = EntityType.Builder.<EntityMagicFireball>create(EntityMagicFireball::new, SpawnGroup.MISC).build("magic_ball");
    
    public static final EntityType<EntityIceBall> iceBall = EntityType.Builder.<EntityIceBall>create(EntityIceBall::new, SpawnGroup.MISC).build("ice_ball");
    public static void init() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(TestMod.MODID, "ice_ball"), iceBall);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(TestMod.MODID, "magic_ball"), magicFireball);
    }
}
