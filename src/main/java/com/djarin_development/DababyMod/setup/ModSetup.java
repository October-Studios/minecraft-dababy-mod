package com.djarin_development.DababyMod.setup;

import com.djarin_development.DababyMod.DababyMod;
import com.djarin_development.DababyMod.blocks.ModBlocks;
import com.djarin_development.DababyMod.entities.DaBabyEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
  public static ItemGroup itemGroup =
      new ItemGroup("dababy") {
        @Override
        public ItemStack makeIcon() {
          return new ItemStack(ModBlocks.DABABYHEAD.get());
        }
      };

  public void init(final FMLCommonSetupEvent event) {
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
              TutorialChunkGenerator.CODEC);
          Registry.register(
              Registry.BIOME_SOURCE,
              new ResourceLocation(DababyMod.MODID, "biomes"),
              TutorialChunkGenerator.CODEC);
        });
  }

  @SubscribeEvent
  public static void serverLoad(RegisterCommandsEvent event) {
    ModCommands.register(event.getDispatcher());
  }
}
