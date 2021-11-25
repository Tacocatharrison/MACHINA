/**
 * This file is part of the Machina Minecraft (Java Edition) mod and is licensed under the MIT license.
 * If you want to contribute please join https://discord.com/invite/x9Mj63m4QG.
 * More information can be found on Github: https://github.com/Cy4Shot/MACHINA
 */

package com.cy4.machina.events;

import com.cy4.machina.Machina;
import com.cy4.machina.api.planet.PlanetUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Machina.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {

	@SubscribeEvent
	public static void fogSetup(FogColors event) {
		if (PlanetUtils.isDimensionPlanet(event.getInfo().getEntity().level.dimension())) {
			event.setBlue(1f);
			event.setRed(0f);
			event.setGreen(1f);
		}
	}
}
