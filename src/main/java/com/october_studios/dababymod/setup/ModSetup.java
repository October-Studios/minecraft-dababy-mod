package com.october_studios.dababymod.setup;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.dimension.ModBiomeProvider;
import com.october_studios.dababymod.dimension.ModChunkGenerator;
import com.october_studios.dababymod.entities.DaBabyEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

  public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("dababy") {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(Registration.DABABYMOB_EGG.get());
    }
  };

  public static void init(final FMLCommonSetupEvent event) {

    event.enqueueWork(
        () -> {

          Registry.register(
              Registry.CHUNK_GENERATOR,
              new ResourceLocation(DababyMod.MODID, "chunkgen"),
              ModChunkGenerator.CODEC);
          Registry.register(
              Registry.BIOME_SOURCE,
              new ResourceLocation(DababyMod.MODID, "biomes"),
              ModBiomeProvider.CODEC);
        });
  }

  public static void onAttributeCreate(EntityAttributeCreationEvent event) {
    event.put(Registration.DABABY.get(), DaBabyEntity.createAttributes().build());
  }

  @SubscribeEvent
  public static void serverLoad(RegisterCommandsEvent event) {
  }
}
