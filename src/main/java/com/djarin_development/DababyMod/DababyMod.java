package com.djarin_development.DababyMod;

import com.djarin_development.DababyMod.setup.ModSetup;;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DababyMod.MODID)
public class DababyMod {

  public static final String MODID = "dababy";

  public static final Logger LOGGER = LogManager.getLogger();

  public DababyMod() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
    ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

    Registration.init();

    // Register the setup method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
  }
}
