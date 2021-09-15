package com.october_studios.dababymod.client.renderer.entity;

import com.october_studios.dababymod.DababyMod;
import com.october_studios.dababymod.entities.DaBabyModel;
import com.october_studios.dababymod.world.entity.DaBabyEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DaBabyRenderer extends HumanoidMobRenderer<Mob, DaBabyModel<Mob>> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(DababyMod.MODID, "textures/entity/dababy.png");

  public DaBabyRenderer(EntityRendererProvider.Context p_174344_, ModelLayerLocation p_174345_, ModelLayerLocation p_174346_, ModelLayerLocation p_174347_, boolean p_174348_) {
    super(p_174344_, createModel(p_174344_.getModelSet(), p_174345_, p_174348_), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
    this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel(p_174344_.bakeLayer(p_174346_)), new HumanoidModel(p_174344_.bakeLayer(p_174347_))));
  }

  private static DaBabyModel<Mob> createModel(EntityModelSet p_174350_, ModelLayerLocation p_174351_, boolean p_174352_) {
    DaBabyModel<Mob> dababymodel = new DaBabyModel<>(p_174350_.bakeLayer(p_174351_));

    return dababymodel;
  }

  public ResourceLocation getTextureLocation(final DaBabyEntity entity) {
    return TEXTURE;
  }
}
