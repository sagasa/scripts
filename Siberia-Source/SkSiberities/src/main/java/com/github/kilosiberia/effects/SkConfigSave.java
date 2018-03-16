package com.github.kilosiberia.effects;

import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;
import com.github.kilosiberia.utils.SkConfigUtils;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SkConfigSave extends Effect {

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "Save script's config";
	}

	@Override
	protected void execute(Event e) {
		SkConfigUtils su = new SkConfigUtils(SkSiberities.getInstance());
		su.saveConfig();
	}

}
