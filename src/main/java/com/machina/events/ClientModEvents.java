package com.machina.events;

import com.machina.Machina;
import com.machina.client.ClientDataHolder;
import com.machina.client.renderer.CargoCrateRenderer;
import com.machina.planet.trait.PlanetTraitSpriteUploader;
import com.machina.registration.init.BlockInit;
import com.machina.registration.init.PlanetAttributeTypesInit;
import com.machina.registration.init.TileEntityTypesInit;
import com.machina.util.PlanetUtils;
import com.machina.util.color.Color;
import com.machina.world.data.PlanetData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.ChunkRenderCache;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Machina.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

	@SubscribeEvent
	public static void onBlockColourHandler(ColorHandlerEvent.Block event) {
		Minecraft minecraft = Minecraft.getInstance();
		PlanetTraitSpriteUploader spriteUploader = new PlanetTraitSpriteUploader(minecraft.textureManager);
		IResourceManager resourceManager = minecraft.getResourceManager();
		if (resourceManager instanceof IReloadableResourceManager) {
			try (IReloadableResourceManager reloadableResourceManager = (IReloadableResourceManager) resourceManager) {
				reloadableResourceManager.registerReloadListener(spriteUploader);
			}
		}
		PlanetTraitSpriteUploader.setInstance(spriteUploader);
	}

	public static IBlockColor getPlanetColor(int defVal, int paletteId) {
		return (state, reader, pos, num) -> {

			World world = null;

			if (reader instanceof World)
				world = (World) reader;

			if (reader instanceof ChunkRenderCache)
				world = ((ChunkRenderCache) reader).level;

			if (world != null) {
				RegistryKey<World> dim = world.dimension();
				if (PlanetUtils.isDimensionPlanet(dim)) {
					PlanetData data = ClientDataHolder.getPlanetDataByID(PlanetUtils.getId(dim));
					Color color = data.getAttribute(PlanetAttributeTypesInit.PALETTE)[paletteId];
					return color.getRGB();
				}
			}

			return defVal;
		};
	}

	@SubscribeEvent
	public static void registerBlockColorsEvent(ColorHandlerEvent.Block event) {
		BlockColors col = event.getBlockColors();

		col.register(getPlanetColor(8947848, 0), BlockInit.ALIEN_STONE.get());
		col.register(getPlanetColor(8947848, 1), BlockInit.TWILIGHT_DIRT.get());
	}

	@SubscribeEvent
	public static void registerRenderers(final FMLClientSetupEvent event) {
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.CARGO_CRATE.get(), CargoCrateRenderer::new);
	}
}