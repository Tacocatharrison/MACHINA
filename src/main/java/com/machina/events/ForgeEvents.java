/**
 * This file is part of the Machina Minecraft (Java Edition) mod and is licensed
 * under the MIT license:
 *
 * MIT License
 *
 * Copyright (c) 2021 Machina Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * If you want to contribute please join https://discord.com/invite/x9Mj63m4QG.
 * More information can be found on Github: https://github.com/Cy4Shot/MACHINA
 */

package com.machina.events;

import static com.machina.api.ModIDs.MACHINA;

import com.machina.Machina;
import com.machina.starchart.StarchartImpl;
import com.machina.world.DynamicDimensionHelper;

import net.minecraft.entity.player.ServerPlayerEntity;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MACHINA, bus = Bus.FORGE)
public class ForgeEvents {

	@SubscribeEvent
	public static void addReloadListeners(AddReloadListenerEvent event) {
		event.addListener(Machina.planetTraitPoolManager);
	}

	@SubscribeEvent
	public static void debug(ItemTossEvent event) {
		if (event.getEntity().level.isClientSide) {
			return;
		}
		StarchartImpl.getDefaultInstance(event.getPlayer().getServer()).debugStarchart();
		DynamicDimensionHelper.sendPlayerToDimension((ServerPlayerEntity) event.getPlayer(),
				DynamicDimensionHelper.createPlanet(event.getPlayer().getServer(), String.valueOf(0)),
				event.getPlayer().position());
	}

	// @SubscribeEvent
	// public static void onWorldLoaded(PlayerEvent.PlayerLoggedInEvent event) {
	// if (!event.getPlayer().level.isClientSide()) {
	// StarchartHelper.syncCapabilityWithClients(event.getPlayer().level);
	// }
	// }

	@SubscribeEvent
	public static void onPlayerLogin(final PlayerLoggedInEvent e) {
		if (e.getEntity().level.isClientSide) {
			return;
		}
		System.out.println("SYNCEY TIME");
//		StarchartData.getDefaultInstance(e.getEntity().getServer()).syncClient((ServerPlayerEntity) e.getPlayer());
	}
}
