package com.october_studios.dababymod.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.october_studios.dababymod.DababyMod;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DaBabyModel<T extends Mob> extends PlayerModel<T> {
  private final PartPose bodyDefault = this.body.storePose();
  private final PartPose headDefault = this.head.storePose();
  private final PartPose leftArmDefault = this.leftArm.storePose();
  private final PartPose rightArmDefault = this.rightArm.storePose();

  public DaBabyModel(ModelPart p_170810_) {
    super(p_170810_, false);
  }

  public static MeshDefinition createMesh(CubeDeformation p_170812_) {
    MeshDefinition meshdefinition = PlayerModel.createMesh(p_170812_, false);
    PartDefinition partdefinition = meshdefinition.getRoot();
    partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, p_170812_), PartPose.ZERO);
    return meshdefinition;
  }

  public void setupAnim(T dababyEntity, float p_103367_, float p_103368_, float p_103369_, float p_103370_, float p_103371_) {
    this.body.loadPose(this.bodyDefault);
    this.head.loadPose(this.headDefault);
    this.leftArm.loadPose(this.leftArmDefault);
    this.rightArm.loadPose(this.rightArmDefault);
    super.setupAnim(dababyEntity, p_103367_, p_103368_, p_103369_, p_103370_, p_103371_);

    this.leftPants.copyFrom(this.leftLeg);
    this.rightPants.copyFrom(this.rightLeg);
    this.leftSleeve.copyFrom(this.leftArm);
    this.rightSleeve.copyFrom(this.rightArm);
    this.jacket.copyFrom(this.body);
    this.hat.copyFrom(this.head);
  }
}
