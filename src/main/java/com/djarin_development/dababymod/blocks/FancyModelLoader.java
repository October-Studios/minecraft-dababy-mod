package com.djarin_development.dababymod.blocks;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class FancyModelLoader implements IModelLoader<FancyModelGeometry> {

  @Override
  public void onResourceManagerReload(IResourceManager resourceManager) {

  }

  @Override
  public FancyModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContext) {
    return new FancyModelGeometry();
  }
}
