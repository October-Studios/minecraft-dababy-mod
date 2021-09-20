package com.october_studios.dababymod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DaBabyModCreativeModeTab extends CreativeModeTab {
  public DaBabyModCreativeModeTab() {
    super(DababyMod.MODID);
  }

  @Override
  public ItemStack makeIcon() {
    return ItemStack.EMPTY;
  }
}
