package com.october_studios.dababymod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

import javax.annotation.Nullable;

public class FirstBlock extends Block {

  public FirstBlock() {
    super(Properties.of(Material.METAL).sound(SoundType.METAL).strength(2.0f));
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return false;
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return defaultBlockState()
        .setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
  }

  @Override
  protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(BlockStateProperties.FACING);
  }
}
