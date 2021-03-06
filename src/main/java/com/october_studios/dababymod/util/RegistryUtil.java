package com.october_studios.dababymod.util;

import com.october_studios.dababymod.DababyMod;
import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author October Studios
 */

public class RegistryUtil {
  /**
   * Get all of this mod's registry entries from the provided registry.
   *
   * @param registry The registry
   * @param <T>      The registry type
   * @return A Set containing the registry entries
   */
  public static <T extends IForgeRegistryEntry<T>> Set<T> getModRegistryEntries(final IForgeRegistry<T> registry) {
    return stream(registry)
        .filter(entry -> entry.getRegistryName() != null && entry.getRegistryName().getNamespace().equals(DababyMod.MODID))
        .collect(Collectors.toSet());
  }

  /**
   * Gets a {@link Stream} of the registry's entries.
   *
   * @param registry The registry
   * @param <T>      The registry type
   * @return A Stream of the registry's entries
   */
  public static <T extends IForgeRegistryEntry<T>> Stream<T> stream(final IForgeRegistry<T> registry) {
    return StreamSupport.stream(registry.spliterator(), false);
  }

  /**
   * Gets the registry name of the {@link IForgeRegistryEntry}, throwing an exception if it's not set.
   *
   * @param entry The registry entry
   * @return The registry name
   * @throws NullPointerException If the registry name is null
   */
  public static ResourceLocation getRequiredRegistryName(final IForgeRegistryEntry<?> entry) {
    return Preconditions.checkNotNull(entry.getRegistryName(), "%s has a null registry name", entry);
  }
}
