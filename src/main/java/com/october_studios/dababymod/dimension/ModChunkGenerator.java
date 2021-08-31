package com.october_studios.dababymod.dimension;

import com.october_studios.dababymod.DababyMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.levelgen.StructureSettings;

public class ModChunkGenerator extends ChunkGenerator {
  private static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
      instance.group(
          Codec.INT.fieldOf("base").forGetter(Settings::getBaseHeight),
          Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::getVerticalVariance),
          Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::getHorizontalVariance)
      ).apply(instance, Settings::new));

  public static final Codec<ModChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
      instance.group(
          RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(ModChunkGenerator::getBiomeRegistry),
          SETTINGS_CODEC.fieldOf("settings").forGetter(ModChunkGenerator::getTutorialSettings)
      ).apply(instance, ModChunkGenerator::new));

  private final Settings settings;

  public ModChunkGenerator(Registry<Biome> registry, Settings settings) {
    super(new ModBiomeProvider(registry), new StructureSettings(false));
    this.settings = settings;
    DababyMod.LOGGER.info("Chunk generator settings: " + settings.getBaseHeight() + ", " + settings.getHorizontalVariance() + ", " + settings.getVerticalVariance());
  }

  public Settings getTutorialSettings() {
    return settings;
  }

  public Registry<Biome> getBiomeRegistry() {
    return ((ModBiomeProvider)biomeSource).getBiomeRegistry();
  }

  @Override
  public void buildSurfaceAndBedrock(WorldGenRegion region, ChunkAccess chunk) {
    BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
    BlockState stone = Blocks.STONE.defaultBlockState();
    ChunkPos chunkpos = chunk.getPos();

    BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

    int x;
    int z;

    for (x = 0; x < 16; x++) {
      for (z = 0; z < 16; z++) {
        chunk.setBlockState(pos.set(x, 0, z), bedrock, false);
      }
    }

    int baseHeight = settings.getBaseHeight();
    float verticalVariance = settings.getVerticalVariance();
    float horizontalVariance = settings.getHorizontalVariance();
    for (x = 0; x < 16; x++) {
      for (z = 0; z < 16; z++) {
        int realx = chunkpos.x * 16 + x;
        int realz = chunkpos.z * 16 + z;
        int height = (int) (baseHeight + Math.sin(realx / horizontalVariance)*verticalVariance + Math.cos(realz / horizontalVariance)*verticalVariance);
        for (int y = 1 ; y < height ; y++) {
          chunk.setBlockState(pos.set(x, y, z), stone, false);
        }
      }
    }
  }

  @Override
  protected Codec<? extends ChunkGenerator> codec() {
    return CODEC;
  }

  @Override
  public ChunkGenerator withSeed(long seed) {
    return new ModChunkGenerator(getBiomeRegistry(), settings);
  }

  @Override
  public void fillFromNoise(LevelAccessor world, StructureFeatureManager structureManager, ChunkAccess chunk) {

  }

  @Override
  public int getBaseHeight(int x, int z, Heightmap.Types heightmapType) {
    return 0;
  }

  @Override
  public BlockGetter getBaseColumn(int p_230348_1_, int p_230348_2_) {
    return new NoiseColumn(new BlockState[0]);
  }

  private static class Settings {
    private final int baseHeight;
    private final float verticalVariance;
    private final float horizontalVariance;

    public Settings(int baseHeight, float verticalVariance, float horizontalVariance) {
      this.baseHeight = baseHeight;
      this.verticalVariance = verticalVariance;
      this.horizontalVariance = horizontalVariance;
    }

    public float getVerticalVariance() {
      return verticalVariance;
    }

    public int getBaseHeight() {
      return baseHeight;
    }

    public float getHorizontalVariance() {
      return horizontalVariance;
    }
  }
}
