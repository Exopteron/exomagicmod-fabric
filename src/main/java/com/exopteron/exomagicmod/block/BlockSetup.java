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
        Registry.register(Registry.BLOCK, new Identifier(TestMod.MODID, "charger_block"), CHARGER_BLOCK);
    }
}
