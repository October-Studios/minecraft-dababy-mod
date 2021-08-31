package com.october_studios.dababymod.dimension;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModBiomeProvider extends BiomeSource {
  public static final Codec<ModBiomeProvider> CODEC = RegistryLookupCodec.create(Registry.BIOME_REGISTRY)
      .xmap(ModBiomeProvider::new, ModBiomeProvider::getBiomeRegistry).codec();

  private final Biome biome;
  private final Registry<Biome> biomeRegistry;
  private static final List<ResourceKey<Biome>> SPAWN = Collections.singletonList(Biomes.PLAINS);

  public ModBiomeProvider(Registry<Biome> biomeRegistry) {
    super(getStartBiomes(biomeRegistry));
    this.biomeRegistry = biomeRegistry;
    biome = biomeRegistry.get(Biomes.PLAINS.location());
  }

  private static List<Biome> getStartBiomes(Registry<Biome> registry) {
    return SPAWN.stream().map(s -> registry.get(s.location())).collect(Collectors.toList());
  }

  public Registry<Biome> getBiomeRegistry() {
    return biomeRegistry;
  }

  @Override
  public boolean canGenerateStructure(StructureFeature<?> structure) {
    return false;
  }

  @Override
  protected Codec<? extends BiomeSource> codec() {
    return CODEC;
  }

  @Override
  public BiomeSource withSeed(long seed) {
    return this;
  }

  @Override
  public Biome getNoiseBiome(int x, int y, int z) {
    return biome;
  }
}
