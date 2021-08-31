package com.october_studios.dababymod.entities;

import com.october_studios.dababymod.entities.DaBabyModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import com.october_studios.dababymod.entities.DaBabyEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class DaBabyRenderer extends AbstractZombieRenderer<DaBabyEntity, DaBabyModel<DaBabyEntity>> {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(DababyMod.MODID, "textures/entity/dababy.png");

  public DaBabyRenderer(Context p_174456_) {
    this(p_174456_, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
  }

  public DaBabyRenderer(Context p_174458_, ModelLayerLocation p_174459_, ModelLayerLocation p_174460_, ModelLayerLocation p_174461_) {
    super(p_174458_, new DaBabyModel(p_174458_.bakeLayer(p_174459_)), new DaBabyModel(p_174458_.bakeLayer(p_174460_)), new DaBabyModel(p_174458_.bakeLayer(p_174461_)));
  }

  @Nullable
  @Override
  public ResourceLocation getTextureLocation(final DaBabyEntity entity) {
    return TEXTURE;
  }
}
