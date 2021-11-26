package com.exopteron.exomagicmod;

import net.minecraft.advancement.Advancement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Utils {
    public static void awardAdvancement(Identifier advancement, ServerPlayerEntity serverPlayer) {
        Advancement adv = serverPlayer.server.getAdvancementLoader().get(advancement);
        for (String str : serverPlayer.getAdvancementTracker().getProgress(adv).getUnobtainedCriteria()) {
            serverPlayer.getAdvancementTracker().grantCriterion(adv, str);
        }
    }
}
