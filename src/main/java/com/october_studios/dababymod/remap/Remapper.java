package com.october_studios.dababymod.remap;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.october_studios.dababymod.DababyMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Remapper<T extends IForgeRegistryEntry<T>> {
  private static final Logger LOGGER = LogManager.getLogger();

  private final List<Predicate<Mapping<T>>> remappingFunctions = ImmutableList.of(this::remapCustomName, this::ignoreName);

  private Remapper() {
  }

  private void remapAll(final List<Mapping<T>> missingMappings) {
    for (final Mapping<T> missingMapping : missingMappings) {
      LOGGER.info("Trying to remap {}", missingMapping.key);

      final boolean remapped = remappingFunctions.stream().anyMatch(mappingPredicate -> mappingPredicate.test(missingMapping));

      if (!remapped) {
        LOGGER.info("Couldn't remap {}", missingMapping.key);
      }
    }
  }

  private boolean tryRemap(final Mapping<T> missingMappping, final ResourceLocation registryName) {
    final IForgeRegistry<T> registry = missingMappping.registry;
    final T value = registry.getValue(registryName);
    if (registry.containsKey(registryName) && value != null) {
      LOGGER.info("Remapped {} {} to {}", registry.getRegistrySuperType().getSimpleName(), missingMappping.key, registryName);
      missingMappping.remap(value);
      return true;
    }

    return false;
  }

  /**
   * Custom names to remap. Keys are the old names, values are the new names.
   */
  private static final Map<String, String> customNames = ImmutableMap.<String, String>builder()
      .put("watergrass", "water_grass")
      .build();

  /**
   * Remap names to those specified in {@link #customNames}.
   *
   * @param missingMapping The missing mapping
   * @return True if the missing mapping was remapped
   */
  private boolean remapCustomName(final Mapping<T> missingMapping) {
    final String missingPath = missingMapping.key.getPath();

    if (!customNames.containsKey(missingPath)) return false;

    final String newPath = customNames.get(missingPath);
    final ResourceLocation newRegistryName = new ResourceLocation(missingMapping.key.getNamespace(), newPath);

    return tryRemap(missingMapping, newRegistryName);
  }

  /**
   * Names to ignore and leave in the save.
   */
  private static final List<String> namesToIgnore = ImmutableList.<String>builder()
      .build();

  private boolean ignoreName(final Mapping<T> missingMapping) {
    final String missingPath = missingMapping.key.getPath();

    if (!namesToIgnore.contains(missingPath)) return false;

    missingMapping.ignore();

    LOGGER.info("Ignored missing {} {}", missingMapping.registry.getRegistrySuperType().getSimpleName(), missingMapping.key);

    return true;
  }

  @Mod.EventBusSubscriber(modid = DababyMod.MODID)
  @SuppressWarnings("unused")
  private static class EventHandler {
    private static final Remapper<Block> blockRemapper = new Remapper<>();
    private static final Remapper<Item> itemRemapper = new Remapper<>();

    @SubscribeEvent
    public static void missingBlockMappings(final RegistryEvent.MissingMappings<Block> event) {
      blockRemapper.remapAll(event.getMappings(DababyMod.MODID));
    }

    @SubscribeEvent
    public static void missingItemMappings(final RegistryEvent.MissingMappings<Item> event) {
      itemRemapper.remapAll(event.getMappings(DababyMod.MODID));
    }
  }
}
