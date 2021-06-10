package com.djarin_development.DababyMod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;

public class DababyHead extends Block {
  public DababyHead() {
    super(
        Properties.of(Material.METAL)
            .sound(SoundType.METAL)
            .strength(2.0f)
            .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0));
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return false;
  }
}
