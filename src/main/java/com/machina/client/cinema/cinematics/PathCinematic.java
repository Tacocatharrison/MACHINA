package com.machina.client.cinema.cinematics;

import java.util.Arrays;
import java.util.List;

import com.machina.client.cinema.CameraPath;
import com.machina.client.cinema.CameraPath.CameraNode;
import com.machina.client.cinema.Cinematic;
import com.machina.client.cinema.effect.CameraEffect;
import com.machina.client.cinema.entity.CameraClientEntity;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

public class PathCinematic extends Cinematic {

	CameraPath path;

	public PathCinematic(ClientPlayerEntity p) {
		super(new CameraClientEntity());
	}
	
	public void setPath(CameraPath path) {
		this.path = path;
	}

	@Override
	public void onClientTick(int tick, float par) {
		if (!this.active)
			return;
		path.tick(this.player, tick, par);
	}

	@Override
	public int getDuration() {
		return path.duration();
	}
	
	public static CameraNode node(double x, double y, double z, float pitch, float yaw) {
		return new CameraNode(new Vector3d(x, y, z), pitch, yaw);
	}

	@Override
	public void onRenderTick(int tick, float par) {
		if (!this.active)
			return;
		path.renderTick(this.player, tick, par);
	}
	
	public static List<CameraEffect> effects(CameraEffect... eff) {
		return Arrays.asList(eff);
	}
}
