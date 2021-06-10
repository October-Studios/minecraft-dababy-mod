package com.djarin_development.DababyMod.setup;

import com.djarin_development.DababyMod.blocks.DababyHead;
import com.djarin_development.DababyMod.entities.DaBabyEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.djarin_development.DababyMod.DababyMod.MODID;

public class Registration {
  private static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
  private static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
  private static final DeferredRegister<EntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

  public static void init() {
    BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public static final RegistryObject<DababyHead> DABABYHEAD =
      BLOCKS.register("dababyhead", DababyHead::new);
  public static final RegistryObject<Item> DABABYHEAD_ITEM =
      ITEMS.register(
          "dababyhead",
          () -> new BlockItem(DABABYHEAD.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));

  public static final RegistryObject<EntityType<DaBabyEntity>> DABABY =
      ENTITIES.register(
          "dababy",
          () ->
              EntityType.Builder.of(DaBabyEntity::new, EntityClassification.MONSTER)
                  .sized(.5f, 5.f)
                  .setShouldReceiveVelocityUpdates(false)
                  .build("dababy"));
}
