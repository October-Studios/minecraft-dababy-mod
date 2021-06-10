package com.djarin_development.DababyMod.entities;

import com.djarin_development.DababyMod.DababyMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DaBabyRenderer extends MobRenderer<DaBabyEntity, DaBabyModel> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(DababyMod.MODID, "textures/entity/dababy.png");

  public DaBabyRenderer(EntityRendererManager manager) {
    super(manager, new DaBabyModel(), 0.5f);
  }

  @Nullable
  @Override
  public ResourceLocation getTextureLocation(DaBabyEntity entity) {
    return TEXTURE;
  }
}
