package com.exopteron.exotestmod.client.entities;

import com.exopteron.exotestmod.entities.EntitySetup;

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
    }
}
