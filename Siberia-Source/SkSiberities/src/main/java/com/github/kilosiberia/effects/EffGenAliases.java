package com.github.kilosiberia.effects;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import siberities.sagasa_1500.GzipMain;

public class EffGenAliases extends Effect{

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "generate mod aliases";
	}

	@Override
	protected void execute(Event e) {
		new GzipMain();
	}

}
