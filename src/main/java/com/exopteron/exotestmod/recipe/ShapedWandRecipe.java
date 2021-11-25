package com.exopteron.exotestmod.recipe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.exopteron.exotestmod.items.ItemEnergyCrystal;
import com.exopteron.exotestmod.items.ItemMagicWand;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import oshi.util.tuples.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class ShapedWandRecipe extends ShapedRecipe {

    public ShapedWandRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> input,
            ItemStack output) {
        super(id, group, width, height, input, output);
        //TODO Auto-generated constructor stub
    }
    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        for (int i = 0; i <= inventory.getWidth() - this.getWidth(); ++i) {
            for (int j = 0; j <= inventory.getHeight() - this.getHeight(); ++j) {
                if (this.matches(inventory, i, j, true)) {
                    return true;
                }

                if (this.matches(inventory, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean matches(CraftingInventory inventory, int width, int height, boolean randomBoolean) {
        for (int i = 0; i < inventory.getWidth(); ++i) {
            for (int j = 0; j < inventory.getHeight(); ++j) {
                int k = i - width;
                int l = j - height;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.getWidth() && l < this.getHeight()) {
                    if (randomBoolean) {
                        ingredient = this.getIngredients().get(this.getWidth() - k - 1 + l * this.getWidth());
                    } else {
                        ingredient = this.getIngredients().get(k + l * this.getWidth());
                    }
                }
                Pair<Boolean, Integer> pair = test(ingredient, inventory.getStack(i + j * inventory.getWidth()));
                if (!pair.getA()) {
                    return false;
                }
            }
        }

        return true;
    }

    public Pair<Boolean, Integer> test(Ingredient ing, ItemStack stack) {
        if (stack == null) {
            return new Pair<Boolean, Integer>(false, 0);
        } else {
            try {
                Method m = ing.getClass().getDeclaredMethod("cacheMatchingStacks");
                m.setAccessible(true);
                m.invoke(ing);
                Field f3 = ing.getClass().getDeclaredField("matchingStacks");
                f3.setAccessible(true);
                ItemStack[] matchingStacks = (ItemStack[]) f3.get(ing);
                if ((int) matchingStacks.length == 0) {
                    return new Pair<Boolean, Integer>(stack.isEmpty(), 0);
                } else {
                    int crystalDamage = 0;
                    for (ItemStack itemstack : (ItemStack[]) matchingStacks) {
                        if (itemstack.isOf(stack.getItem())) {
                            if (itemstack.getItem() instanceof ItemEnergyCrystal) {
                                crystalDamage = ItemMagicWand.getCrystalDamage(stack);
                                if (this.getOutput().getItem() instanceof ItemMagicWand) {
                                    try {
                                        Field f4 = ShapedRecipe.class.getDeclaredField("output");
                                        f4.setAccessible(true);
                                        ItemStack resultStack = this.getOutput();
                                        //System.out.println("B: " + pair.getB());
                                        ItemEnergyCrystal.setCrystalDamage(resultStack, crystalDamage);
                                        f4.set(this, resultStack);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            return new Pair<Boolean, Integer>(true, crystalDamage);
                        }
                    }

                    return new Pair<Boolean, Integer>(false, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new Pair<Boolean, Integer>(false, 0);
            }
        }
    }
}
