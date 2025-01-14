package com.machina.trait;

import com.machina.planet.trait.PlanetTrait;
import com.machina.planet.trait.type.IWorldTrait;
import com.machina.world.PlanetChunkGenerator;

public class WaterHeightTrait extends PlanetTrait implements IWorldTrait {

	final int height;

	public WaterHeightTrait(int color, int h) {
		super(color);

		height = h;
	}

	@Override
	public void init(PlanetChunkGenerator chunkGenerator) {
		chunkGenerator.seaLevel = height;
	}
}