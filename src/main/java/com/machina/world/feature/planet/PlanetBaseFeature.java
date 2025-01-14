package com.machina.world.feature.planet;

import java.util.Random;

import com.machina.planet.attribute.PlanetAttributeList;
import com.machina.world.PlanetChunkGenerator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.Placement;

public abstract class PlanetBaseFeature extends Feature<NoFeatureConfig> {
	
	protected PlanetAttributeList attr;

	public PlanetBaseFeature(PlanetAttributeList attr) {
		super(NoFeatureConfig.CODEC);
		this.attr = attr;
	}

	@Override
	public boolean place(ISeedReader region, ChunkGenerator chunk, Random rand, BlockPos pos, NoFeatureConfig conf) {
		return place(region, (PlanetChunkGenerator) chunk, rand, pos); // DODGE!!
	}
	
	public abstract boolean place(ISeedReader region, PlanetChunkGenerator chunk, Random rand, BlockPos pos);

	public ConfiguredFeature<NoFeatureConfig, ?> config() {
		return this.configured(new NoFeatureConfig());
	}

	public ConfiguredFeature<?, ?> count(int count) {
		return this.config().decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(count)));
	}
}