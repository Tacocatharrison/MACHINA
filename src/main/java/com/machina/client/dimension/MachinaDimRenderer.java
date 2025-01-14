package com.machina.client.dimension;

import org.lwjgl.opengl.GL11;

import com.machina.Machina;
import com.machina.client.util.QuadBufferRenderer;
import com.machina.util.text.MachinaRL;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ICloudRenderHandler;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.client.IWeatherParticleRenderHandler;
import net.minecraftforge.client.IWeatherRenderHandler;

@OnlyIn(Dist.CLIENT)
public class MachinaDimRenderer extends DimensionRenderInfo {

	public static void registerDimensionRenderInfo() {
		Machina.LOGGER.warn("Registering Machina Planet Render Info.");
		DimensionRenderInfo.EFFECTS.put(Machina.MACHINA_ID, new MachinaDimRenderer());
	}

	public MachinaDimRenderer() {
		super(Float.NaN, false, FogType.NONE, true, false);

		this.setSkyRenderHandler(new CustomSkyRenderer());
		this.setCloudRenderHandler(new CustomCloudRenderer());
		this.setWeatherRenderHandler(new CustomWeatherRenderer());
		this.setWeatherParticleRenderHandler(new CustomWeatherParticleRenderer());
	}

	@Override
	public Vector3d getBrightnessDependentFogColor(Vector3d color, float scale) {
		return color.scale(scale);
	}

	@Override
	public boolean isFoggyAt(int x, int y) {
		return true;
	}

	@Override
	public float[] getSunriseColor(float p_230492_1_, float p_230492_2_) {
		return null;
	}

	public static class CustomSkyRenderer implements ISkyRenderHandler {

		public static final ResourceLocation STARS = new MachinaRL("textures/environment/sky/starfield.png");
		public static final ResourceLocation FOG = new MachinaRL("textures/environment/sky/fog.png");

		private VertexBuffer sky;
		private VertexBuffer fog;

		public CustomSkyRenderer() {
			sky = QuadBufferRenderer.create(sky, bb -> QuadBufferRenderer.cube(bb, 100));
			fog = QuadBufferRenderer.create(fog, bb -> QuadBufferRenderer.cylinder(bb, 10, 70, 100));
		}

		@Override
		public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {
			TextureManager tm = mc.getTextureManager();
			int currTicks = mc.levelRenderer.ticks;
			float time = (currTicks % 360000) * 0.000017453292F;

			RenderSystem.enableTexture();
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glAlphaFunc(516, 0.0F);
			GL11.glEnable(GL11.GL_BLEND);
			RenderSystem.depthMask(false);

			tm.bind(STARS);
			QuadBufferRenderer.render(matrixStack, sky, DefaultVertexFormats.POSITION_TEX, FogRenderer.fogRed,
					FogRenderer.fogGreen, FogRenderer.fogBlue, 0.8f);

			matrixStack.pushPose();
			matrixStack.last().pose().multiply(new Quaternion(0, -time * 4, 0, false));
			tm.bind(FOG);
			QuadBufferRenderer.render(matrixStack, fog, DefaultVertexFormats.POSITION_TEX, FogRenderer.fogRed,
					FogRenderer.fogGreen, FogRenderer.fogBlue, 1f);
			matrixStack.popPose();

			RenderSystem.depthMask(true);
		}
	}

	public static class CustomCloudRenderer implements ICloudRenderHandler {

		@Override
		public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc,
				double viewEntityX, double viewEntityY, double viewEntityZ) {
		}
	}

	public static class CustomWeatherRenderer implements IWeatherRenderHandler {

		@Override
		public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc, LightTexture lightmapIn,
				double xIn, double yIn, double zIn) {
		}
	}

	public static class CustomWeatherParticleRenderer implements IWeatherParticleRenderHandler {

		@Override
		public void render(int ticks, ClientWorld world, Minecraft mc, ActiveRenderInfo activeRenderInfoIn) {

		}
	}
}
