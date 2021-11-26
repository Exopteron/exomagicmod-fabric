package com.exopteron.exotestmod.items.tooltip;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import java.lang.reflect.Field;
import java.text.Format;
import java.util.List;

import com.exopteron.exotestmod.client.keybind.KeybindSetup;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class TooltippableBlockItem extends BlockItem {
    private Text extraInfo;
    public TooltippableBlockItem(Block block, Settings settings) {
        super(block, settings);
        //TODO Auto-generated constructor stub
    }
    public TooltippableBlockItem(Block block, Settings settings, Text info) {
        super(block, settings);
        this.extraInfo = info.copy().formatted(Formatting.GRAY);
        //TODO Auto-generated constructor stub
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        TooltippableItem.toolTip(tooltip, this.extraTooltipData());
        super.appendTooltip(stack, world, tooltip, context);
    }
    public TooltippableBlockItem setExtraInfo(Text info) {
        this.extraInfo = info;
        return this;
    }
    public Text extraTooltipData() {
        return this.extraInfo;
    }
}