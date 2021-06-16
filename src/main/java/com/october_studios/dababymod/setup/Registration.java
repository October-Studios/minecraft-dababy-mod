package com.october_studios.dababymod.setup;

import com.october_studios.dababymod.entities.DaBabyEntity;
import com.october_studios.dababymod.items.DaBabyMobEggItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.october_studios.dababymod.DababyMod.MODID;

public class Registration {
  private static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
  private static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
  private static final DeferredRegister<TileEntityType<?>> TILES =
      DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
  private static final DeferredRegister<EntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
  private static final DeferredRegister<SoundEvent> SOUNDS =
      DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

  public static void init() {
    BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public static final RegistryObject<SoundEvent> DABABY_SOUND =
      SOUNDS.register(
          "mob.letsgo",
          () -> new SoundEvent((new ResourceLocation(MODID, "mob.letsgo"))));

  public static final RegistryObject<DaBabyMobEggItem> DABABYMOB_EGG =
      ITEMS.register("dababymob_egg", DaBabyMobEggItem::new);

  public static final RegistryObject<EntityType<DaBabyEntity>> DABABY =
      ENTITIES.register(
          "dababy",
          () ->
              EntityType.Builder.of(DaBabyEntity::new, EntityClassification.CREATURE)
                  .sized(1, 1)
                  .setShouldReceiveVelocityUpdates(false)
                  .build("dababy"));
}
