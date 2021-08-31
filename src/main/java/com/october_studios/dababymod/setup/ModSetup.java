package com.october_studios.dababymod.setup;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.dimension.ModBiomeProvider;
import com.october_studios.dababymod.dimension.ModChunkGenerator;
import com.october_studios.dababymod.entities.DaBabyEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraftforge.common.MinecraftForge;
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
    MinecraftForge.EVENT_BUS.addGenericListener(
        Entity.class, ChargeEventHandler::onAttachCapabilitiesEvent);
    MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onAttackEvent);
    MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onDeathEvent);

    event.enqueueWork(
        () -> {
          DefaultAttributes.put(
              Registration.DABABY.get(), DaBabyEntity.prepareAttributes().build());

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
}
