package com.cy4.machina.network;

import com.cy4.machina.Machina;
import com.cy4.machina.api.network.BaseNetwork;
import com.cy4.machina.network.message.to_server.NotifySendTraitsMessage;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class MachinaNetwork extends BaseNetwork {
	
	public static final String NETWORK_VERSION = "0.1.0";
	
	public static final SimpleChannel CHANNEL = newSimpleChannel("channel");
	
	public static void init() {
		registerClientToServer(CHANNEL, NotifySendTraitsMessage.class, NotifySendTraitsMessage::decode);
	}
	
	private static SimpleChannel newSimpleChannel(String name) {
		return NetworkRegistry.newSimpleChannel(
				new ResourceLocation(Machina.MOD_ID, name), () -> NETWORK_VERSION,
				version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	}

}
