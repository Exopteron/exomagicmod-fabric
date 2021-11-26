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
package com.exopteron.exomagicmod.client.gui;

import com.exopteron.exomagicmod.TestMod;
import com.exopteron.exomagicmod.items.ItemMagicWand;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells.SpellRegistry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.awt.Color;

@Environment(value = EnvType.CLIENT)
public class GuiMagicWand implements HudRenderCallback {

        @Override
        public void onHudRender(MatrixStack matrixStack, float tickDelta) {
                MinecraftClient mc = MinecraftClient.getInstance();
                ClientPlayerEntity player = mc.player;
                ItemStack heldItem = player.getInventory().getStack(player.getInventory().selectedSlot);
                if (heldItem.getItem() instanceof ItemMagicWand) {
                        int windowHeight = mc.getWindow().getHeight();
                        NbtCompound tag = heldItem.getOrCreateNbt();
                        // draws box
                        InGameHud.fill(matrixStack, 0, windowHeight / 2, 100, (windowHeight / 2) - 45,
                                        new Color(0, 0, 0, 140).getRGB());
                        // draws durability indicator
                        InGameHud.drawStringWithShadow(matrixStack, mc.textRenderer,
                                        "Durability: " + (150 - heldItem.getDamage()), 5, (windowHeight / 2) - 35,
                                        0xD3D3D3);
                        // draws spell indicator
                        String currentSpellID = tag.getString("CurrentSpell");
                        String displayName = SpellRegistry.INSTANCE.clientSpellRegistry.get(currentSpellID);
                        if (displayName == null) {
                                displayName = currentSpellID;
                                if (currentSpellID != "") {
                                        TestMod.LOGGER.error("[Exo's Magic Mod] Unrecognised spell " + currentSpellID
                                                        + " on wand!");
                                }
                        }
                        InGameHud.drawStringWithShadow(matrixStack, mc.textRenderer, "Spell: " + displayName, 5,
                                        (windowHeight / 2) - 25, 0xD3D3D3);
                        // draws crystal damage indicator
                        InGameHud.drawStringWithShadow(matrixStack, mc.textRenderer,
                                        "Crystal Damage: " + tag.getInt("CrystalDamage"), 5, (windowHeight / 2) - 15,
                                        0xD3D3D3);
                }
        }

}
