package com.github.kilosiberia.expressions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.minecraft.util.org.apache.commons.codec.digest.DigestUtils;

public class ExprServerHash extends SimpleExpression<String> {

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "get server pack hash";
	}

	@Override
	protected String[] get(Event e) {

		File folder = new File(System.getProperty("user.dir"), "Flan");

		if (folder.exists())
			try {
				File[] files = folder.listFiles();
				File file = null;
				for (File value : files) {
					if (value.getName().contains("HiddenField"))
						file = value;
				}

				if (file != null)
					return new String[] { DigestUtils.sha256Hex(new FileInputStream(file)) };

				else
					return null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		return null;
	}

}
