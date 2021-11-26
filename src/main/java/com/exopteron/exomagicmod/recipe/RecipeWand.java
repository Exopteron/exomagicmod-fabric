package com.exopteron.exomagicmod.recipe;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.items.ItemEnergyCrystal;
import com.exopteron.exomagicmod.items.ItemSetup;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class RecipeWand implements RecipeSerializer<ShapedRecipe> {

    @Override
    public ShapedRecipe read(Identifier id, JsonObject json) {
        DefaultedList<Ingredient> recipeItems = DefaultedList.ofSize(1);
        ItemStack energyCrystal = new ItemStack(ItemSetup.ENERGY_CRYSTAL);
        ItemEnergyCrystal.setCrystalDamage(energyCrystal, 0);
        recipeItems.add(Ingredient.ofStacks(energyCrystal));
        recipeItems.add(Ingredient.ofStacks(ItemSetup.NETHERITE_STICK.getDefaultStack()));
        ItemStack wand = new ItemStack(ItemSetup.MAGIC_WAND);
        ItemEnergyCrystal.setCrystalDamage(wand, 0);
        return new ShapedWandRecipe(id, "wand1", 1, 2, recipeItems, wand);
    }

    @Override
    public ShapedRecipe read(Identifier id, PacketByteBuf buf) {
        int i = buf.readVarInt();
        int j = buf.readVarInt();
        String s = buf.readString();
        DefaultedList<Ingredient> nonnulllist = DefaultedList.ofSize(i * j, Ingredient.EMPTY);

        for (int k = 0; k < nonnulllist.size(); ++k) {
            nonnulllist.set(k, Ingredient.fromPacket(buf));
        }

        ItemStack itemstack = buf.readItemStack();
        return new ShapedRecipe(id, s, i, j, nonnulllist, itemstack);
    }

    @Override
    public void write(PacketByteBuf buf, ShapedRecipe recipe) {
        buf.writeVarInt(recipe.getWidth());
        buf.writeVarInt(recipe.getHeight());
        buf.writeString(recipe.getGroup());

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.write(buf);
        }

        buf.writeItemStack(recipe.getOutput());
        
    }
    
}
