package com.october_studios.dababymod.init;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.world.item.DaBabyMobEggItem;
import net.minecraft.world.entity.EntityType;
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

  public static final RegistryObject<DaBabyMobEggItem> DABABY_MOB_EGG = registerSpawnEgg("dababy_mob_egg",
      ModEntities.DABABY_MOB, 0xDA70B, 0);

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

  /**
   * Registers a spawn egg item.
   *
   * @param name           The registry name of the item
   * @param entityType     The entity type to spawn
   * @param primaryColor   The primary colour of the spawn egg
   * @param secondaryColor The secondary colour of the spawn egg
   * @return A RegistryObject reference to the item
   */
  private static RegistryObject<DaBabyMobEggItem> registerSpawnEgg(
      final String name, final RegistryObject<? extends EntityType<?>> entityType,
      final int primaryColor, final int secondaryColor
  ) {
    final RegistryObject<DaBabyMobEggItem> spawnEgg = ITEMS.register(name,
        () -> new DaBabyMobEggItem(entityType, primaryColor, secondaryColor, new Item.Properties().tab(DababyMod.CREATIVE_MODE_TAB))
    );

    SPAWN_EGGS.add(spawnEgg);

    return spawnEgg;
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
