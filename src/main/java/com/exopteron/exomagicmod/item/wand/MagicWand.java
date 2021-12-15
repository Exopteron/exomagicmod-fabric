package com.exopteron.exomagicmod.item.wand;

import java.util.List;

import com.exopteron.exomagicmod.item.wand.spell.SpellManager;
import com.exopteron.exomagicmod.item.wand.spell.WandSpell;
import com.exopteron.exomagicmod.item.wand.spell.SpellManager.Spells;
import com.exopteron.exomagicmod.network.NetworkSetup;
import com.exopteron.exomagicmod.network.packet.PacketCastSpell;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MagicWand extends Item {
    private Crystal crystal;

    public Crystal getCrystal() {
        return this.crystal;
    }

    public MagicWand(Crystal crystal) {
        super(new FabricItemSettings());
        this.crystal = crystal;
    }

    @Override
    public Text getName(ItemStack stack) {
        // TODO Auto-generated method stub
        return getName();
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.getMana(stack) < maxMana()) {
            setMana(stack, this.getMana(stack) + 1);
        }
    }
    @Override
    public Text getName() {
        // Placeholder for now
        return new TranslatableText("wands.exomagicmod." + this.getCrystal().getWandName());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getStackInHand(hand);
        Spells spell = SpellManager.Spells.getSpell(item);
        if (!((MagicWand) item.getItem()).canCastSpell(user, item, spell.getSpell(), true)) {
            return TypedActionResult.fail(item);
        }
        if (world.isClient) {
            PacketByteBuf buf = PacketByteBufs.create();
            spell.getSpell().castClient(item, buf);
            NetworkSetup.INSTANCE.sendPacketToServer(new PacketCastSpell(spell.name(), buf, hand));
            return TypedActionResult.success(item);
        }
        return TypedActionResult.success(item);
    }

    public int maxMana() {
        return 500;
    };

    public int getMana(ItemStack i) {
        NbtCompound tag = i.getOrCreateNbt();
        if (tag.contains("CurrentMana")) {
            return tag.getInt("CurrentMana");
        } else {
            tag.putInt("CurrentMana", this.maxMana());
            i.setNbt(tag);
            return this.maxMana();
        }
    }

    public static void setMana(ItemStack i, int mana) {
        NbtCompound tag = i.getOrCreateNbt();
        tag.putInt("CurrentMana", mana);
        i.setNbt(tag);
    }

    public boolean canCastSpell(PlayerEntity player, ItemStack i, WandSpell spell, boolean client) {
        if (player.getItemCooldownManager().isCoolingDown(i.getItem())) {
            return false;
        }
        if (getMana(i) >= spell.manaUsage()) {
            if (!client) {
                setMana(i, getMana(i) - spell.manaUsage());
            }
            return true;
        } else {
            if (client) {
                player.sendMessage(Text.of("Not enough mana!").copy().formatted(Formatting.RED), true);
            }
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Mana: " + getMana(stack)));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
