package com.exopteron.exomagicmod.commands;

import java.util.List;

import com.exopteron.exomagicmod.config.SpellConfig;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells;
import com.exopteron.exomagicmod.items.spells.MagicWandSpells.SpellRegistry;
import com.exopteron.exomagicmod.network.ExoNetworkManager;
import com.exopteron.exomagicmod.network.packet.PacketSpellRegistry;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CommandsSetup {
    public static void init(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("emm_reload").requires(source -> source.hasPermissionLevel(4)).executes(new CommandsSetup().new ReloadCommand()));
    }
    public class ReloadCommand implements Command<ServerCommandSource> {
        @Override
        public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
            context.getSource().sendFeedback(Text.of("Reloading spell registry"), true);
            SpellRegistry.INSTANCE = new MagicWandSpells().new SpellRegistry();
            List<ServerPlayerEntity> allPlayers = context.getSource().getServer().getPlayerManager().getPlayerList();
            for (ServerPlayerEntity p : allPlayers) {
                ExoNetworkManager.INSTANCE.sendPacketToClient(PacketSpellRegistry.fromSpellRegistry(), p);
            }
            SpellConfig.reload();
            return 1;
        }
    }
}
