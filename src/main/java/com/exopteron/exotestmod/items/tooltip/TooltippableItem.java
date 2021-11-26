package com.exopteron.exotestmod.items.tooltip;

import java.lang.reflect.Field;
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
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class TooltippableItem extends Item {
    private Text extraInfo;
    public TooltippableItem(Settings settings) {
        super(settings);
    }
    public TooltippableItem(Settings settings, Text info) {
        super(settings);
        this.extraInfo = info.copy().formatted(Formatting.GRAY);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        toolTip(tooltip, this.extraTooltipData());
        super.appendTooltip(stack, world, tooltip, context);
    }
    public static void toolTip(List<Text> tooltip, Text extraData) {
        try {
            Field f = KeyBinding.class.getDeclaredField("boundKey");
            f.setAccessible(true);
            int keyCode = ((InputUtil.Key) f.get(KeybindSetup.showToolTip)).getCode();
            if (GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), keyCode) == GLFW.GLFW_PRESS) {
                tooltip.add(extraData);
            } else {
                tooltip.add(Text.of("Press ").copy().formatted(Formatting.DARK_GRAY)
                        .append(Text.of("[" + new TranslatableText(KeybindSetup.showToolTip.getBoundKeyTranslationKey()).getString() + "]").copy().formatted(Formatting.GRAY)
                                .append(Text.of(" to view info").copy().formatted(Formatting.DARK_GRAY))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TooltippableItem setExtraInfo(Text info) {
        this.extraInfo = info;
        return this;
    }
    public Text extraTooltipData() {
        return this.extraInfo;
    }
}
