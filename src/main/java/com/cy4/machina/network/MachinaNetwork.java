package com.cy4.machina.network;

import java.util.function.Function;

import com.cy4.machina.api.network.BaseNetwork;
import com.cy4.machina.api.network.message.IMachinaMessage;
import com.cy4.machina.network.message.to_client.SyncTraitsCapabilityMessage;
import com.cy4.machina.network.message.to_server.DevPlanetCreationGUIMessage;
import com.cy4.machina.network.message.to_server.RequestTraitsUpdateMessage;
import com.cy4.machina.util.MachinaRL;

import net.minecraft.network.PacketBuffer;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class MachinaNetwork extends BaseNetwork {

	public static final String NETWORK_VERSION = "0.1.0";

	public static final SimpleChannel CHANNEL = newSimpleChannel("channel");

	public static void init() {
		registerServerToClient(CHANNEL, SyncTraitsCapabilityMessage.class, SyncTraitsCapabilityMessage::decode);
		registerClientToServer(RequestTraitsUpdateMessage.class, RequestTraitsUpdateMessage::decode);

		registerClientToServer(DevPlanetCreationGUIMessage.class, DevPlanetCreationGUIMessage::decode);
	}

	private static SimpleChannel newSimpleChannel(String name) {
		return NetworkRegistry.newSimpleChannel(new MachinaRL(name), () -> NETWORK_VERSION,
				version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	}

	private static <M extends IMachinaMessage> void registerClientToServer(Class<M> type,
			Function<PacketBuffer, M> decoder) {
		registerClientToServer(CHANNEL, type, decoder);
	}

}