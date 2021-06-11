package com.djarin_development.dababymod.entities;

import com.djarin_development.dababymod.DababyMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DaBabyRenderer extends MobRenderer<DaBabyEntity, DaBabyModel> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(DababyMod.MODID, "textures/entity/dababy.png");

  public DaBabyRenderer(final EntityRendererManager manager) {
    super(manager, new DaBabyModel(0.0F), 0.5f);
    super.addLayer(new BipedArmorLayer<>(this, new DaBabyModel(0.5F), new DaBabyModel(1.0F)));
  }

  @Nullable
  @Override
  public ResourceLocation getTextureLocation(final DaBabyEntity entity) {
    return TEXTURE;
  }
}
