package com.cy4.machina.api.capability.planet_data;

import java.util.LinkedList;
import java.util.List;

import com.cy4.machina.api.planet.trait.PlanetTrait;

public class DefaultPlanetDataCapability implements IPlanetDataCapability {

	private List<PlanetTrait> traits = new LinkedList<>();
	private String name = "Planet";

	@Override
	public void addTrait(PlanetTrait trait) {
		if (!traits.contains(trait)) {
			traits.add(trait);
		}
	}

	@Override
	public void removeTrait(PlanetTrait trait) {
		traits.remove(trait);
	}

	@Override
	public List<PlanetTrait> getTraits() { return traits; }

	@Override
	public String getName() { return name; }

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
