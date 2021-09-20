package com.october_studios.dababymod.init;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.data.DaBabyModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataProviders {
  @SubscribeEvent
  public static void registerDataProviders(final GatherDataEvent event) {
    final DataGenerator dataGenerator = event.getGenerator();
    final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

    if(event.includeClient()) {
      final DaBabyModItemModelProvider itemModelProvider = new DaBabyModItemModelProvider(dataGenerator, existingFileHelper);
      dataGenerator.addProvider(itemModelProvider);
    }
  }
}
