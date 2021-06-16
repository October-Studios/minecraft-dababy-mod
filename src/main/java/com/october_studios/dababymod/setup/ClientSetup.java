package com.october_studios.dababymod.setup;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.client.AfterLivingRenderer;
import com.october_studios.dababymod.client.InWorldRenderer;
import com.october_studios.dababymod.entities.DaBabyRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
    modid = DababyMod.MODID,
    value = Dist.CLIENT,
    bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
  public static void init(final FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(
        Registration.DABABY.get(), DaBabyRenderer::new);
    MinecraftForge.EVENT_BUS.addListener(InWorldRenderer::render);
    MinecraftForge.EVENT_BUS.addListener(AfterLivingRenderer::render);
  }

  @SubscribeEvent
  public static void onItemColor(ColorHandlerEvent.Item event) {
    event.getItemColors().register((stack, i) -> 0xff0000, Registration.DABABYMOB_EGG.get());
  }

  @SubscribeEvent
  public static void onModelRegistryEvent(ModelRegistryEvent event) {}

  @SubscribeEvent
  public static void onTextureStitch(TextureStitchEvent.Pre event) {
    if (!event.getMap().location().equals(AtlasTexture.LOCATION_BLOCKS)) {
      return;
    }
  }

  @SubscribeEvent
  public void onTooltipPre(RenderTooltipEvent.Pre event) {
    Item item = event.getStack().getItem();
    if (item.getRegistryName().getNamespace().equals(DababyMod.MODID)) {
      event.setMaxWidth(200);
    }
  }
}
