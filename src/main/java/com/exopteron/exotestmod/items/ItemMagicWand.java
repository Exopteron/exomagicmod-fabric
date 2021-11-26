package com.exopteron.exotestmod.items;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.exopteron.exotestmod.TestMod;
import com.exopteron.exotestmod.Utils;
import com.exopteron.exotestmod.items.spells.IWandSpell;
import com.exopteron.exotestmod.items.spells.MagicWandSpells;
import com.exopteron.exotestmod.items.spells.MagicWandSpells.SpellRegistry;
import com.exopteron.exotestmod.items.tooltip.TooltippableItem;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;



public class ItemMagicWand extends TooltippableItem {

    public ItemMagicWand(Settings settings) {
        super(settings);
        //TODO Auto-generated constructor stub
    }
    public static int getCrystalDamage(ItemStack wand) {
        NbtCompound tag = wand.getOrCreateNbt();
        int crystalDamage = tag.getInt("CrystalDamage");
        return crystalDamage;
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Crystal Damage: " + getCrystalDamage(stack)).copy().formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack i = user.getStackInHand(hand);
        String spell = MagicWandSpells.spellFromItemStack(i);
        boolean doRebound = shouldRebound(i);
        if (!world.isClient) {
            IWandSpell s = SpellRegistry.INSTANCE.spellRegistry.get(spell).getA();
            if (s == null) {
                return super.use(world, user, hand);
            }
            if (doRebound) {
                Utils.awardAdvancement(new Identifier(TestMod.MODID, "wand_rebound"), (ServerPlayerEntity) user);
                user.sendMessage(new TranslatableText("exotestmod.spellrebound").formatted(Formatting.ITALIC).formatted(Formatting.GRAY), false);
                damageWand(i, s.getSpellDurabilityCost(), user, hand);
                s.rebound(world, user, hand, i);
            } else {
                Utils.awardAdvancement(new Identifier(TestMod.MODID, "cast_spell"), (ServerPlayerEntity) user);
                int cooldownTicks = s.cast(world, user, hand, i);
                damageWand(i, s.getSpellDurabilityCost(), user, hand);
                user.getItemCooldownManager().set(this, cooldownTicks);
            }
        } else {
            user.swingHand(hand);
        }
        return super.use(world, user, hand);
    }
    public static boolean shouldRebound(ItemStack wand) {
        int crystalDamage = getCrystalDamage(wand);
        if (crystalDamage == 0) {
            return false;
        }
        Random random = ThreadLocalRandom.current();
        return (random.nextInt(100) <= 10 * crystalDamage);
    }
    public static void damageWand(ItemStack wand, int damage, PlayerEntity player, Hand hand) {
        int crystalDamage = getCrystalDamage(wand);
        ItemStack crystal = ItemSetup.ENERGY_CRYSTAL.getDefaultStack();
        wand.damage(damage, player, (item) -> {
            Utils.awardAdvancement(new Identifier(TestMod.MODID, "wand_break"), (ServerPlayerEntity) player);
            //Utils.awardAdvancement(new ResourceLocation("exotestmod", "wand_break"), (ServerPlayer) player);
            if ((crystalDamage + 1) < 11) {
                player.world.createExplosion(player, player.getTrackedPosition().x, player.getTrackedPosition().y, player.getTrackedPosition().z, 1.5F, true, DestructionType.NONE);
                ItemEnergyCrystal.setCrystalDamage(crystal, crystalDamage + 1);
                player.world.spawnEntity(new ItemEntity(player.world, player.getTrackedPosition().x, player.getTrackedPosition().y + 1,
                        player.getTrackedPosition().z, crystal, 0D, 0D, 0D));
            } else {
                Utils.awardAdvancement(new Identifier(TestMod.MODID, "wand_explosion"), (ServerPlayerEntity) player);
                //Utils.awardAdvancement(new ResourceLocation("exotestmod", "wand_explosion"), (ServerPlayer) player);
                player.world.createExplosion(player, player.getTrackedPosition().x, player.getTrackedPosition().y, player.getTrackedPosition().z, 4.0F, true,
                        DestructionType.BREAK);
            }
            player.world.spawnEntity(new ItemEntity(player.world, player.getTrackedPosition().x, player.getTrackedPosition().y + 1,
                    player.getTrackedPosition().z, new ItemStack(ItemSetup.NETHERITE_STICK), 0D, 0D, 0D));
            player.sendToolBreakStatus(hand);
        });
    }
    @Override
    public Text extraTooltipData() {
        return Text.of("It's a magic wand. It's pretty cool.").copy().formatted(Formatting.DARK_GRAY);
    }
}
