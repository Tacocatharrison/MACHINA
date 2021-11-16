package com.cy4.machina.block.fluid;

import java.util.function.Supplier;

import com.cy4.machina.init.DamageSourcesInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LiquidHydrogenBlock extends FlowingFluidBlock {

	public LiquidHydrogenBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
		super(supplier, properties);
	}

	private int ticksSinceLastDmg;

	@Override
	public void entityInside(BlockState pState, World pLevel, BlockPos pPos, Entity pEntity) {
		ticksSinceLastDmg++;
		if (ticksSinceLastDmg >= 40) {
			pEntity.hurt(DamageSourcesInit.LIQUID_HYDROGEN_DAMAGE_SOURCE, 2.0f);
			ticksSinceLastDmg = 0;
		}
	}

}