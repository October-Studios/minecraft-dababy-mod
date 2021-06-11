package com.djarin_development.dababymod.setup;

import com.djarin_development.dababymod.DababyMod;
import com.djarin_development.dababymod.data.CapabilityEntityCharge;
import com.djarin_development.dababymod.data.ChargeEventHandler;
import com.djarin_development.dababymod.dimension.ModBiomeProvider;
import com.djarin_development.dababymod.dimension.ModChunkGenerator;
import com.djarin_development.dababymod.entities.DaBabyEntity;
import com.djarin_development.dababymod.network.Networking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

  public static final ItemGroup ITEM_GROUP = new ItemGroup("dababy") {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(Registration.FIRSTBLOCK.get());
    }
  };

  public static void init(final FMLCommonSetupEvent event) {
    Networking.registerMessage();
    CapabilityEntityCharge.register();

    MinecraftForge.EVENT_BUS.addGenericListener(
        Entity.class, ChargeEventHandler::onAttachCapabilitiesEvent);
    MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onAttackEvent);
    MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onDeathEvent);

    event.enqueueWork(
        () -> {
          GlobalEntityTypeAttributes.put(
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
