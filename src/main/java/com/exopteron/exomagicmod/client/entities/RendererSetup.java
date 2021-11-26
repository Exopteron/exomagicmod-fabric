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
package com.exopteron.exomagicmod.client.entities;

import com.exopteron.exomagicmod.entities.EntitySetup;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.registry.Registry;

public class RendererSetup {
    public static void init() {
        EntityRendererRegistry.register(EntitySetup.iceBall, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntitySetup.magicFireball, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntitySetup.thrownTorch, FlyingItemEntityRenderer::new);
    }
}
