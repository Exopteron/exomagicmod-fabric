package com.exopteron.exomagicmod.items.tooltip;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import java.lang.reflect.Field;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import com.exopteron.exomagicmod.client.keybind.KeybindSetup;

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
    private List<Text> extraInfo = new ArrayList<>();
    public TooltippableBlockItem(Block block, Settings settings) {
        super(block, settings);
        //TODO Auto-generated constructor stub
    }
    public TooltippableBlockItem(Block block, Settings settings, Text info) {
        super(block, settings);
        this.extraInfo.add(info.copy().formatted(Formatting.GRAY));
        //TODO Auto-generated constructor stub
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        TooltippableItem.toolTip(tooltip, this.extraTooltipData());
        super.appendTooltip(stack, world, tooltip, context);
    }
    public TooltippableBlockItem addExtraInfo(Text info) {
        this.extraInfo.add(info);
        return this;
    }
    public List<Text> extraTooltipData() {
        return this.extraInfo;
    }
}
