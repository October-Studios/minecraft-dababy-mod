package com.october_studios.dababymod.init;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.world.entity.DaBabyEntity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public class ModEntities {
  private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, DababyMod.MODID);

  private static boolean isInitialized;

  public static final RegistryObject<EntityType<DaBabyEntity>> DABABY_MOB = registerEntityType("dababy_mob", () -> EntityType.Builder.<DaBabyEntity>of((DaBabyEntity::new), MobCategory.MONSTER).sized(0.6f, 1.7f));

  public static void initialize(final IEventBus modEventBus) {
    if (isInitialized) {
      throw new IllegalStateException("Already initialized");
    }

    ENTITIES.register(modEventBus);

    isInitialized = true;
  }

  private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(final String name, final Supplier<EntityType.Builder<T>> factory) {
    return ENTITIES.register(name, () -> factory.get().build(new ResourceLocation(DababyMod.MODID, name).toString()));
  }

  @Mod.EventBusSubscriber(modid = DababyMod.MODID, bus = Bus.MOD)
  public static class RegistrationHandler {
    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {
      event.put(DABABY_MOB.get(), DaBabyEntity.registerAttributes().build());
    }
  }

  @Mod.EventBusSubscriber(modid = DababyMod.MODID)
  public static class SpawnHandler {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerEntitySpawns(final BiomeLoadingEvent event) {
      if (event.getName() == null) return;

      final ResourceKey<Biome> biomeRegistryKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());

      if (BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.OCEAN)) {
        addSpawn(event, EntityType.GUARDIAN, 100, 5, 20, MobCategory.WATER_CREATURE);
      }
    }

    private static void addSpawn(final BiomeLoadingEvent event, final EntityType<? extends Mob> entityType, final int itemWeight, final int minGroupCount, final int maxGroupCount, final MobCategory classification) {
      final List<MobSpawnSettings.SpawnerData> spawnersList = event.getSpawns().getSpawner(classification);

      spawnersList.stream().filter(spawners -> spawners.type == entityType).findFirst().ifPresent(spawnersList::remove);

      spawnersList.add(new MobSpawnSettings.SpawnerData(entityType, itemWeight, minGroupCount, maxGroupCount));
    }
  }
}
