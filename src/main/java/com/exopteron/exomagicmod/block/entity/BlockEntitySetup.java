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
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(TestMod.MODID, "charger_block_entity"), CHARGER_BLOCK_ENTITY);
    }
}
