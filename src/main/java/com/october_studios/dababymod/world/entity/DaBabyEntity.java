package com.october_studios.dababymod.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class DaBabyEntity extends Monster{

  public DaBabyEntity(final EntityType<? extends Monster> dababyEntity, final Level world) {
    super(dababyEntity, world);
    this.xpReward = 5;
  }

  public static AttributeSupplier.Builder registerAttributes() {
    return Monster.createMonsterAttributes();
  }
}