package com.october_studios.dababymod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class DaBabyEntity extends Monster{

  public DaBabyEntity(EntityType<? extends DaBabyEntity> dababyEntity, Level world) {
    super(dababyEntity, world);
    this.xpReward = 5;
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 16.0D);
  }
}