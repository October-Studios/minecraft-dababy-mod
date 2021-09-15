package com.october_studios.dababymod.client.renderer.entity;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = DababyMod.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModRenderers {
  @SubscribeEvent
  public static void register(final EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(ModEntities.DABABY_MOB.get(), context -> new DaBabyRenderer(context));
  }
}
