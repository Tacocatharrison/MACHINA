package com.machina.command.impl;

import com.machina.command.BaseCommand;
import com.machina.world.PlanetRegistrationHandler;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.world.server.ServerWorld;

public class GoToPlanetCommand extends BaseCommand {

	public GoToPlanetCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.then(Commands.argument("planet_id", IntegerArgumentType.integer())
				.executes(ctx -> execute(ctx, IntegerArgumentType.getInteger(ctx, "planet_id"))));
	}

	protected int execute(CommandContext<CommandSource> context, int planetId) {
		ServerWorld world = PlanetRegistrationHandler.createPlanet(context.getSource().getServer(),
				String.valueOf(planetId));
		try {
			PlanetRegistrationHandler.sendPlayerToDimension(context.getSource().getPlayerOrException(), world,
					context.getSource().getPosition());
		} catch (CommandSyntaxException e) {}

		return 0;
	}

	@Override
	public String getName() { return "go_to_planet"; }

}
