package com.djarin_development.dababymod.datagen;

import com.djarin_development.dababymod.DababyMod;
import com.djarin_development.dababymod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {
  public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, DababyMod.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    withExistingParent(
        Registration.FIRSTBLOCK_ITEM.get().getRegistryName().getPath(),
        new ResourceLocation(DababyMod.MODID, "block/firstblock"));
  }
}
