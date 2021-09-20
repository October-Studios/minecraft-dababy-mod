package com.october_studios.dababymod.client.renderer.entity;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.entities.DaBabyModel;
import com.october_studios.dababymod.world.entity.DaBabyEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DaBabyRenderer extends HumanoidMobRenderer<DaBabyEntity, DaBabyModel<DaBabyEntity>> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(DababyMod.MODID, "textures/entity/dababy.png");

  public DaBabyRenderer(EntityRendererProvider.Context p_174380_) {
    this(p_174380_, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
  }

  public DaBabyRenderer(
      EntityRendererProvider.Context p_174382_,
      ModelLayerLocation p_174383_,
      ModelLayerLocation p_174384_,
      ModelLayerLocation p_174385_) {
    super(p_174382_, new DaBabyModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
    this.addLayer(
        new HumanoidArmorLayer<>(
            this,
            new DaBabyModel(p_174382_.bakeLayer(p_174384_)),
            new DaBabyModel(p_174382_.bakeLayer(p_174385_))));
  }

  public ResourceLocation getTextureLocation(final DaBabyEntity entity) {
    return TEXTURE;
  }
}
