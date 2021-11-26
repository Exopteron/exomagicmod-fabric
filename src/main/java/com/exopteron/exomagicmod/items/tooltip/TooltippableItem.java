package com.exopteron.exomagicmod.items.tooltip;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.exopteron.exomagicmod.client.keybind.KeybindSetup;
import com.exopteron.exomagicmod.mixin.MixinKeybinding;

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
    private List<Text> extraInfo = new ArrayList<>();
    public TooltippableItem(Settings settings) {
        super(settings);
    }
    public TooltippableItem(Settings settings, Text info) {
        super(settings);
        this.extraInfo.add(info.copy().formatted(Formatting.GRAY));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        toolTip(tooltip, this.extraTooltipData());
        super.appendTooltip(stack, world, tooltip, context);
    }
    public static void toolTip(List<Text> tooltip, List<Text> extraData) {
        try {
            int keyCode = ((MixinKeybinding) KeybindSetup.showToolTip).getBoundKey().getCode();
            if (GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), keyCode) == GLFW.GLFW_PRESS) {
                tooltip.addAll(extraData);
            } else {
                tooltip.add(Text.of("Press ").copy().formatted(Formatting.DARK_GRAY)
                        .append(Text.of("[" + new TranslatableText(KeybindSetup.showToolTip.getBoundKeyTranslationKey()).getString() + "]").copy().formatted(Formatting.GRAY)
                                .append(Text.of(" to view info").copy().formatted(Formatting.DARK_GRAY))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TooltippableItem addExtraInfo(Text info) {
        this.extraInfo.add(info.copy().formatted(Formatting.GRAY));
        return this;
    }
    public List<Text> extraTooltipData() {
        return this.extraInfo;
    }
}
