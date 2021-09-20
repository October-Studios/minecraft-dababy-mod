package com.october_studios.dababymod.data;

import com.google.common.base.Preconditions;
import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DaBabyModItemModelProvider extends ItemModelProvider {
  private static final String LAYER_0 = "layer0";

  public DaBabyModItemModelProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
    super(generator, DababyMod.MODID, existingFileHelper);
  }

  @Override
  public String getName(){
    return "DaBabyModItemModels";
  }

  @Override
  protected void registerModels() {
    spawnEggItem(ModItems.DABABY_MOB_EGG.get());
  }

  private ResourceLocation registryName(final Item item) {
    return Preconditions.checkNotNull(item.getRegistryName(), "Item %s has a null registry name", item);
  }

  private String name(final Item item) {
    return registryName(item).getPath();
  }

  private ItemModelBuilder withExistingParent(final Item item, final Item modelItem) {
    return withExistingParent(name(item), registryName(modelItem));
  }

  private void spawnEggItem(final Item item) {
    withExistingParent(name(item), mcLoc("dababy_spawn_egg"));
  }
}
