package com.october_studios.dababymod.entities;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DaBabyEntity extends Animal {

  public DaBabyEntity(final EntityType<? extends Animal> type, final Level world) {
    super(type, world);
  }

  @Nullable
  @Override
  public AgableMob getBreedOffspring(
      @Nonnull ServerLevel world, @Nonnull AgableMob entity) {
    return null;
  }

  public static AttributeSupplier.Builder prepareAttributes() {
    return LivingEntity.createLivingAttributes()
        .add(Attributes.ATTACK_DAMAGE, 3.0D)
        .add(Attributes.MAX_HEALTH, 20.0D)
        .add(Attributes.FOLLOW_RANGE, 40.0D);
  }
}