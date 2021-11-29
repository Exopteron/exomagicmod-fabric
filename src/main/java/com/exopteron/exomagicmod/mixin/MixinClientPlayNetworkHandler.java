/* 
Copyright (c) 2021 Exopteron 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.exopteron.exomagicmod.mixin;

import com.exopteron.exomagicmod.callbacks.ClientJoinGameCallback;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ExperienceOrbSpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
/*     @Redirect(method = "onExperienceOrbSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;addEntity(ILnet/minecraft/entity/Entity;)V"))
    private void redirect(ClientWorld world, int id, Entity e) {
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putString("id", EntityType.getId(e.getType()).toString());
        ExperienceOrbEntity entity22 = (ExperienceOrbEntity) EntityType.loadEntityWithPassengers(nbtCompound, world, entity -> {
            entity.refreshPositionAndAngles(new BlockPos(e.getTrackedPosition()), entity.getYaw(), entity.getPitch());
            return entity;
        });
        world.addEntity(id, entity22);
    } */
    @Inject(method = "onGameJoin", at = @At("TAIL"))
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo c) {
        MinecraftClient.getInstance().world.isThundering();
        ClientJoinGameCallback.EVENT.invoker().call();
    }
}
