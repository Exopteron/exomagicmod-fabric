package com.exopteron.exomagicmod.client.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.awt.Color;

import com.exopteron.exomagicmod.item.ItemSetup;
import com.exopteron.exomagicmod.item.wand.MagicWand;
import com.exopteron.exomagicmod.item.wand.spell.SpellManager.Spells;
public class WandOverlay extends OverlayGuiCotton {
    WLabel manaLabel = new WLabel(new LiteralText(""), new Color(0, 0, 0, 0).getRGB());
    WLabel currentSpellLabel = new WLabel(new LiteralText(""), new Color(0, 0, 0, 0).getRGB());
    WLabel previousSpellLabel = new WLabel(new LiteralText(""), new Color(0, 0, 0, 0).getRGB());
    WLabel nextSpellLabel = new WLabel(new LiteralText(""), new Color(0, 0, 0, 0).getRGB());
    public WandOverlay() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setLocation(10, 10);
        root.setSize(140, 65);
        root.setInsets(Insets.ROOT_PANEL);
        root.setBackgroundPainter(BackgroundPainter.VANILLA);
        
        root.add(manaLabel, 0, 0, 2, 1);
        root.add(previousSpellLabel, 5, 0, 0, 0);
        root.add(currentSpellLabel, 5, 1, 0, 0);
        root.add(nextSpellLabel, 5, 2, 0, 0);
        currentSpellLabel.setLocation(currentSpellLabel.getX() - 10, currentSpellLabel.getY());
        nextSpellLabel.setLocation(nextSpellLabel.getX() - 7, nextSpellLabel.getY());
        previousSpellLabel.setLocation(previousSpellLabel.getX() - 7, previousSpellLabel.getY());
    }

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        ItemStack activeWand = null;
        if (mc.player.getMainHandStack().getItem() instanceof MagicWand) {
            activeWand = mc.player.getMainHandStack();
        } else if (mc.player.getOffHandStack().getItem() instanceof MagicWand) {
            activeWand = mc.player.getOffHandStack();
        }
        MagicWand wand = ((MagicWand) activeWand.getItem());
        manaLabel.setText(Text.of("Mana: " + wand.getMana(activeWand) + "/" + wand.maxMana()));
        Spells spell = Spells.getSpell(activeWand);
        previousSpellLabel.setText(Text.of(spell.offset(-1).name()));
        currentSpellLabel.setText(Text.of("[" + spell.name() + "]"));
        nextSpellLabel.setText(Text.of(spell.offset(1).name()));
        int windowHeight = mc.getWindow().getHeight();
        int windowWidth = mc.getWindow().getWidth();
        int bottomLeft = (windowHeight / 2) - this.getRootPanel().getHeight();
        int bottomRight = (windowWidth / 2) - this.getRootPanel().getWidth();
        this.getRootPanel().setLocation(bottomRight - 5, bottomLeft - 5);
    }
    @Override
    public boolean shouldDraw() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player.getMainHandStack().getItem() instanceof MagicWand) {
            return true;
        }
        if (mc.player.getOffHandStack().getItem() instanceof MagicWand) {
            return true;
        }
        return false;
    }
}
