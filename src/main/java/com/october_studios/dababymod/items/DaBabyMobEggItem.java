package com.october_studios.dababymod.items;

import com.october_studios.dababymod.setup.ModSetup;
import com.october_studios.dababymod.setup.Registration;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraftforge.common.util.Constants;

import java.util.Objects;

public class DaBabyMobEggItem extends Item {
  public DaBabyMobEggItem() {
    super(new Item.Properties().stacksTo(1).tab(ModSetup.ITEM_GROUP));
  }

  /** Called when this item is used targeting a Block */
  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level world = context.getLevel();
    if (!world.isClientSide) {
      ItemStack itemStack = context.getItemInHand();
      BlockPos blockPos = context.getClickedPos();
      Direction direction = context.getClickedFace();
      BlockState blockState = world.getBlockState(blockPos);
      BlockEntity tileEntity = world.getBlockEntity(blockPos);
      if (tileEntity instanceof SpawnerBlockEntity) {
        BaseSpawner abstractSpawner = ((SpawnerBlockEntity) tileEntity).getSpawner();
        abstractSpawner.setEntityId(Registration.DABABY.get());
        tileEntity.setChanged();
        world.sendBlockUpdated(
            blockPos,
            blockState,
            blockState,
            Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
        itemStack.shrink(1);
        return InteractionResult.SUCCESS;
      }

      BlockPos blockPos1;
      if (blockState.getCollisionShape(world, blockPos).isEmpty()) {
        blockPos1 = blockPos;
      } else {
        blockPos1 = blockPos.relative(direction);
      }

      if (Registration.DABABY
          .get()
          .spawn(
              (ServerLevel) world,
              itemStack,
              context.getPlayer(),
              blockPos1,
              MobSpawnType.SPAWN_EGG,
              true,
              !Objects.equals(blockPos, blockPos1) && direction == Direction.UP)
          != null) {
        itemStack.shrink(1);
      }

    }
    return InteractionResult.SUCCESS;
  }
}
