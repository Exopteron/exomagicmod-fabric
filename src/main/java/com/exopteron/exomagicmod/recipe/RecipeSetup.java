package com.exopteron.exomagicmod.recipe;

import com.exopteron.exomagicmod.TestMod;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeSetup {
    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(TestMod.MODID, "wand_recipe"), new RecipeWand());
    }
}
