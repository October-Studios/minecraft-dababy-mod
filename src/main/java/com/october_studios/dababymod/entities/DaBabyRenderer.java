package com.october_studios.dababymod.entities;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.entities.DaBabyModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

import javax.annotation.Nullable;

public class DaBabyRenderer extends MobRenderer<DaBabyEntity, DaBabyModel> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(DababyMod.MODID, "textures/entity/dababy.png");

  public DaBabyRenderer(EntityRendererProvider.Context context) {
    super(context, new DaBabyModel(context.getModelSet().bakeLayer(DaBabyModel.BODY_LAYER)), 0.5f);
  }

  @Nullable
  @Override
  public ResourceLocation getTextureLocation(final DaBabyEntity entity) {
    return TEXTURE;
  }
}
