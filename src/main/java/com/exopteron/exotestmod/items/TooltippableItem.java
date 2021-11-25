package com.exopteron.exotestmod.items;

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
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class TooltippableItem extends Item {
    private Text extraInfo;
    public TooltippableItem(Settings settings) {
        super(settings);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        try {
            Field f = KeyBinding.class.getDeclaredField("boundKey");
            f.setAccessible(true);
            if (GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), ((InputUtil.Key) f.get(KeybindSetup.showToolTip)).getCode()) == GLFW.GLFW_PRESS) {
                tooltip.add(this.extraTooltipData());
            } else {
                tooltip.add(Text.of("Press ").copy().formatted(Formatting.DARK_GRAY)
                        .append(Text.of("[LSHIFT]").copy().formatted(Formatting.GRAY)
                                .append(Text.of(" to view info").copy().formatted(Formatting.DARK_GRAY))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
    public TooltippableItem setExtraInfo(Text info) {
        this.extraInfo = info;
        return this;
    }
    public Text extraTooltipData() {
        return this.extraInfo;
    }
}
