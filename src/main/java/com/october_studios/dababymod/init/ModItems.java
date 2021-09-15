package com.october_studios.dababymod.init;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.world.item.DaBabyMobEggItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class ModItems {
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DababyMod.MODID);

  private static final Set<RegistryObject<DaBabyMobEggItem>> SPAWN_EGGS = new HashSet<>();

  private static boolean isInitialized;

  public static void initialize(final IEventBus modEventBus) {
    if (isInitialized) {
      throw new IllegalStateException("Already initialized");
    }

    ITEMS.register(modEventBus);

    isInitialized = true;
  }

  public static Collection<? extends Supplier<DaBabyMobEggItem>> getSpawnEggs() {
    return Collections.unmodifiableCollection(SPAWN_EGGS);
  }

  @Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Bus.MOD)
  public static class EventHandler{
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
      event.enqueueWork(() ->
            DaBabyMobEggItem.addEggsToEggsMap(getSpawnEggs())
          );
    }
  }
}
