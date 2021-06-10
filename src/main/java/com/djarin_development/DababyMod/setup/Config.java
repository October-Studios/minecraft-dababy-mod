package com.djarin_development.DababyMod.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubcriber
public class Config {
  public static final String CATEGORY_GENERAL = "general";
  public static final String CATEGORY_POWER = "power";
  public static final String SUBCATEGORY_DABABYHEAD = "dababyhead";

  public static ForgeConfigSpec SERVER_CONFIG;
  public static ForgeConfigSpec CLIENT_CONFIG;

  @SubscribeEvent
  public static void onLoad(final ModConfig.Loading configEvent) {}

  @SubscribeEvent
  public static void onReload(final ModConfig.Reloading configEvent) {}
}
