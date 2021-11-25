/**
 * This code is part of the Machina Minecraft (Java Edition) mod and is licensed under the MIT license.
 * If you want to contribute please join https://discord.com/invite/x9Mj63m4QG.
 * More information can be found on Github: https://github.com/Cy4Shot/MACHINA
 */

package com.cy4.machina.util;

import static com.cy4.machina.Machina.MOD_ID;

import com.cy4.machina.Machina;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.text.TranslationTextComponent;

public class MachinaRL extends ResourceLocation {

	private static final SimpleCommandExceptionType ERROR_INVALID = new SimpleCommandExceptionType(
			new TranslationTextComponent("argument.id.invalid"));

	public MachinaRL(int id) {
		super(MOD_ID, String.valueOf(id));
	}
	
	public MachinaRL(String name) {
		super(checkModId(name));
	}

	public MachinaRL(String modId, String path) {
		super(modId, path);
	}

	public static String checkModId(String input) {
		return input.contains(":") ? input : Machina.MOD_ID + ":" + input;
	}

	public static MachinaRL read(StringReader pReader) throws CommandSyntaxException {
		int i = pReader.getCursor();

		while (pReader.canRead() && isAllowedInResourceLocation(pReader.peek())) {
			pReader.skip();
		}

		String s = pReader.getString().substring(i, pReader.getCursor());

		try {
			return new MachinaRL(s);
		} catch (ResourceLocationException resourcelocationexception) {
			pReader.setCursor(i);
			throw ERROR_INVALID.createWithContext(pReader);
		}
	}

}
