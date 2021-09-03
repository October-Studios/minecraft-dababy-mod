package com.october_studios.dababymod.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber
public class Config {
  public static final String CATEGORY_GENERAL = "general";
  public static final String CATEGORY_POWER = "power";
  public static final String SUBCATEGORY_FIRSTBLOCK = "firstblock";

  public static ForgeConfigSpec SERVER_CONFIG;
  public static ForgeConfigSpec CLIENT_CONFIG;

  public static ForgeConfigSpec.IntValue FIRSTBLOCK_GENERATE;

  static {
    ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
    CLIENT_BUILDER.pop();

    SERVER_BUILDER.comment("Power settings").push(CATEGORY_POWER);

    setupFirstBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);

    SERVER_BUILDER.pop();

    SERVER_CONFIG = SERVER_BUILDER.build();
    CLIENT_CONFIG = CLIENT_BUILDER.build();
  }

  private static void setupFirstBlockConfig(
      ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
    SERVER_BUILDER.comment("FirstBlock settings").push(SUBCATEGORY_FIRSTBLOCK);

    FIRSTBLOCK_GENERATE =
        SERVER_BUILDER
            .comment("Power generation per diamond")
            .defineInRange("generate", 1000, 0, Integer.MAX_VALUE);

    SERVER_BUILDER.pop();
  }

  @SubscribeEvent
  public static void onLoad(final ModConfigEvent.Loading configEvent) {

  }

  @SubscribeEvent
  public static void onReload(final ModConfigEvent.Reloading configEvent) {
  }
}