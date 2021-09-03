package com.october_studios.dababymod.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.october_studios.dababymod.DababyMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class DaBabyModel extends EntityModel<DaBabyEntity> {

  public static final String BODY = "body";

  public static ModelLayerLocation BODY_LAYER = new ModelLayerLocation(new ResourceLocation(DababyMod.MODID, "dababy"), BODY);

  private final ModelPart body;
  public DaBabyModel(ModelPart p_171090_) {
    this.body = p_171090_;
  }

  @Override
  public void setupAnim(DaBabyEntity weirdMobEntity, float v, float v1, float v2, float v3, float v4) {

  }

  @Override
  public void renderToBuffer(PoseStack matrixStack, VertexConsumer builder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    body.render(matrixStack, builder, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }
}
