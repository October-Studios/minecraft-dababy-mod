package com.october_studios.dababymod.setup;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.client.AfterLivingRenderer;
import com.october_studios.dababymod.client.InWorldRenderer;
import com.october_studios.dababymod.entities.DaBabyRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
    modid = DababyMod.MODID,
    value = Dist.CLIENT,
    bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
  public static void init(final FMLClientSetupEvent event) {
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
    if (!event.getMap().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
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
