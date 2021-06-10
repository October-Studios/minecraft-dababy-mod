package com.djarin_development.DababyMod.setup;

import com.djarin_development.DababyMod.entities.DaBabyRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
  public static void init(final FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(Registration.DABABY.get(), DaBabyRenderer::new);
    MinecraftForge.EVENT_BUS.addListener(InWorldRenderer::render);
    MinecraftForge.EVENT_BUS.addListener(AfterLivingRenderer::render);
  }
}
