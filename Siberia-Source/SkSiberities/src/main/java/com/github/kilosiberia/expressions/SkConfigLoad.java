package com.github.kilosiberia.expressions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;
import com.github.kilosiberia.utils.SkConfigUtils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class SkConfigLoad extends SimpleExpression<String> {

	private Expression<String> path;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.path = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "Save script's config";
	}

	@Override
	protected String[] get(Event e) {
		try {
			FileConfiguration cfg = new SkConfigUtils(SkSiberities.getInstance()).getConfig();
			String value = cfg.getString(path.getSingle(e));
			return new String[] { value };
		} catch (NullPointerException ex) {
			return null;
		}
	}

}
