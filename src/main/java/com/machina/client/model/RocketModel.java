package com.machina.client.model;

import com.machina.client.util.MachinaRenderTypes;
import com.machina.util.text.MachinaRL;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

// Model by Reider
public class RocketModel extends EntityModel<Entity> {

	private final ModelRenderer lifesupport;
	private final ModelRenderer shield;
	private final ModelRenderer core;
	private final ModelRenderer reactor;
	private final ModelRenderer cube_r1;
	private final ModelRenderer thrusters;
	private final ModelRenderer[] modelParts;

	public RocketModel() {
		texWidth = 128;
		texHeight = 128;

		lifesupport = new ModelRenderer(this);
		lifesupport.setPos(0.0F, 24.0F, 0.0F);
		lifesupport.texOffs(0, 0).addBox(-8.0F, -76.0F, -8.0F, 16.0F, 32.0F, 16.0F, -0.01F, false);

		shield = new ModelRenderer(this);
		shield.setPos(0.0F, 24.0F, 0.0F);
		shield.texOffs(64, 64).addBox(-6.0F, -87.0F, -6.0F, 12.0F, 11.0F, 12.0F, 0.0F, false);

		core = new ModelRenderer(this);
		core.setPos(0.0F, 24.0F, 0.0F);
		core.texOffs(48, 32).addBox(-8.0F, -44.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		core.texOffs(0, 80).addBox(-6.0F, -41.0F, -6.0F, 12.0F, 10.0F, 12.0F, 0.0F, false);
		core.texOffs(48, 87).addBox(-4.0F, -44.0F, -4.0F, 8.0F, 16.0F, 8.0F, -0.001F, false);

		reactor = new ModelRenderer(this);
		reactor.setPos(0.0F, 24.0F, 0.0F);
		reactor.texOffs(0, 48).addBox(-8.0F, -28.0F, -8.0F, 16.0F, 16.0F, 16.0F, -0.01F, false);
		reactor.texOffs(65, 1).addBox(-5.0F, -27.0F, -8.0F, 10.0F, 14.0F, 15.0F, -0.01F, false);
		reactor.texOffs(0, 0).addBox(-2.0F, -28.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		reactor.texOffs(96, 30).addBox(-3.0F, -24.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
		reactor.texOffs(36, 80).addBox(-6.0F, -21.0F, 0.0F, 12.0F, 6.0F, 0.0F, 0.0F, false);
		reactor.texOffs(48, 0).addBox(-4.0F, -19.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -18.0F, 0.0F);
		reactor.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.6144F, 0.0F);
		cube_r1.texOffs(36, 80).addBox(-6.0F, -3.0F, 0.0F, 12.0F, 6.0F, 0.0F, 0.0F, false);

		thrusters = new ModelRenderer(this);
		thrusters.setPos(0.0F, 24.0F, 0.0F);
		thrusters.texOffs(80, 87).addBox(6.0F, -26.0F, -11.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);
		thrusters.texOffs(80, 87).addBox(-12.0F, -26.0F, -11.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);
		thrusters.texOffs(80, 87).addBox(-3.0F, -26.0F, 6.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);
		thrusters.texOffs(100, 0).addBox(-2.5F, -12.0F, -6.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		thrusters.texOffs(100, 0).addBox(-6.0F, -12.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		thrusters.texOffs(100, 0).addBox(1.0F, -12.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		modelParts = new ModelRenderer[] { reactor, core, thrusters, lifesupport, shield };
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack s, IVertexBuilder buff, int l, int o, float r, float g, float b, float a) {
		lifesupport.render(s, buff, l, o, r, g, b, a);
		shield.render(s, buff, l, o, r, g, b, a);
		core.render(s, buff, l, o, r, g, b, a);
		reactor.render(s, buff, l, o, r, g, b, a);
		thrusters.render(s, buff, l, o, r, g, b, a);
	}

	public void partRender(MatrixStack s, int l, int o, float r, float g, float b, float a, int p, boolean c) {
		for (int i = 0; i < 5; i++) {
			if (p > i) {
				final ModelRenderer part = modelParts[i];
				MachinaRenderTypes.doWithType(rocket(), builder -> part.render(s, builder, l, o, r, g, b, a));
			} else if (p == i) {
				final ModelRenderer part = modelParts[i];
				MachinaRenderTypes.doWithType(preview(c), builder -> part.render(s, builder, l, o, r, g, b, a));
			}
		}
	}

	private static final ResourceLocation ROCKET = new MachinaRL("textures/rocket/rocket.png");

	public RenderType rocket() {
		return renderType(ROCKET);
	}

	public RenderType preview(boolean construct) {
		return construct ? MachinaRenderTypes.ROCKET_CONSTRUCT : MachinaRenderTypes.ROCKET_MISSING;
	}

	public void setRotationAngle(ModelRenderer renderer, float x, float y, float z) {
		renderer.xRot = x;
		renderer.yRot = y;
		renderer.zRot = z;
	}
}