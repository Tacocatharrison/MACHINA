/**
 * This file is part of the Machina Minecraft (Java Edition) mod and is licensed under the MIT license.
 * If you want to contribute please join https://discord.com/invite/x9Mj63m4QG.
 * More information can be found on Github: https://github.com/Cy4Shot/MACHINA
 */

package com.cy4.machina.world;

import com.cy4.machina.Machina;
import com.cy4.machina.util.MachinaRL;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;

public class DynamicDimensionFactory {

	public static final RegistryKey<DimensionType> TYPE_KEY = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY,
			Machina.MACHINA_ID);

	public static final RegistryKey<DimensionType> SUPERHOT_KEY = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY,
			new MachinaRL("superhot"));

	public static Dimension createDimension(MinecraftServer server, RegistryKey<Dimension> key) {
		return new Dimension(() -> getDimensionType(server), new DynamicDimensionChunkGenerator(server, key));
	}

	public static DimensionType getDimensionType(MinecraftServer server) {
		return server.registryAccess().registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).getOrThrow(TYPE_KEY);
	}
}
