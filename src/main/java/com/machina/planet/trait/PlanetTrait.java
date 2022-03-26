package com.machina.planet.trait;

import com.machina.registration.init.PlanetTraitInit;
import com.machina.util.color.Color;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class PlanetTrait extends ForgeRegistryEntry<PlanetTrait> {

	private final int color;

	public PlanetTrait(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return new TranslationTextComponent(getRegistryName().getNamespace() + ".planet_trait." + getRegistryName().getPath()).getString();
	}

	public boolean exists() {
		return this != PlanetTraitInit.NONE;
	}

	@SuppressWarnings("deprecation")
	public void render(MatrixStack matrixStack, int xPosition, int yPosition, boolean coloured) {
		Minecraft minecraft = Minecraft.getInstance();
		TextureAtlasSprite textureatlassprite = getSprite();
		minecraft.getTextureManager().bind(textureatlassprite.atlas().location());
		Color colour = new Color(getColor());
		float[] compFloat = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
		float[] colourArray = colour.getComponents(compFloat);
		if (coloured) {
			RenderSystem.color4f(colourArray[0], colourArray[1], colourArray[2], colourArray[3]);
		} else {
			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		}
		AbstractGui.blit(matrixStack, xPosition, yPosition, 12, 16, 16, textureatlassprite);
	}

	public int getColor() {
		return color;
	}

	public TextureAtlasSprite getSprite() {
		return PlanetTraitSpriteUploader.getFromInstance(this);
	}

}