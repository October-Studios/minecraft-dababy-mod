package com.october_studios.dababymod;

import com.october_studios.dababymod.config.DaBabyModConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DababyMod.MODID)
@Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Bus.MOD)
public class DababyMod {
  public static final Logger LOGGER = LogManager.getLogger();

  public static final String MODID = "dababymod";
  public static final String NAME = "DaBaby Mod";

  public DababyMod() {
    DaBabyModConfig.register(ModLoadingContext.get());
  }
}
