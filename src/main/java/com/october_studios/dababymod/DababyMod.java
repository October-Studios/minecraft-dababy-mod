package com.october_studios.dababymod;

import com.october_studios.dababymod.config.DaBabyModConfig;
import com.october_studios.dababymod.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DababyMod.MODID)
@Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Bus.MOD)
public class DababyMod {
  public static final Logger LOGGER = LogManager.getLogger();

  public static final String MODID = "dababymod";
  public static final String NAME = "DaBaby Mod";

  public static final DaBabyModCreativeModeTab CREATIVE_MODE_TAB = new DaBabyModCreativeModeTab();

  public DababyMod() {
    DaBabyModConfig.register(ModLoadingContext.get());

    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ModItems.initialize(modEventBus);
    ModEntities.initialize(modEventBus);
  }
}
