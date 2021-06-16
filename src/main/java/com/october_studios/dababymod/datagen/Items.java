package com.october_studios.dababymod.datagen;

import com.october_studios.dababymod.DababyMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {
  public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, DababyMod.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
  }
}
