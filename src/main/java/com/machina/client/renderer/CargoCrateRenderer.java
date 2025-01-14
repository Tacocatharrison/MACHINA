package com.machina.client.renderer;

import com.machina.block.tile.CargoCrateTileEntity;
import com.machina.client.model.CustomBlockModel;
import com.machina.client.util.TERUtil;
import com.machina.util.text.StringUtils;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CargoCrateRenderer extends GeoBlockRenderer<CargoCrateTileEntity> {

	private Minecraft mc = Minecraft.getInstance();

	public CargoCrateRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn, CustomBlockModel.create("cargo_crate"));
	}

	@Override
	public void render(TileEntity te, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		super.render(te, partialTicks, stack, bufferIn, combinedLightIn, combinedOverlayIn);

		CargoCrateTileEntity ccte = (CargoCrateTileEntity) te;
		int lightLevel = TERUtil.getLightLevel(te.getLevel(), te.getBlockPos());

		TERUtil.renderItem(ccte.getItem(0), new double[] { .5d, .4d, .5d }, new Vector3f(0, 180f - mc.player.yRot, 0),
				stack, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.8f);

		if (ccte.open && !ccte.getItem(0).isEmpty()) {
			TERUtil.renderLabel(stack, bufferIn, lightLevel, new double[] { .5d, .9d, .5d },
					StringUtils.translateCompScreen("cargo_crate.open"), 0xffffff);
		}
	}
}