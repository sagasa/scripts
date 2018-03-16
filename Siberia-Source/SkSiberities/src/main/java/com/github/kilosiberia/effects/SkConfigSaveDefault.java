package com.github.kilosiberia.effects;

import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;
import com.github.kilosiberia.utils.SkConfigUtils;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SkConfigSaveDefault extends Effect {

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "Save default script's config";
	}

	@Override
	protected void execute(Event e) {
		new SkConfigUtils(SkSiberities.getInstance()).saveDefaultConfig();
	}

}
