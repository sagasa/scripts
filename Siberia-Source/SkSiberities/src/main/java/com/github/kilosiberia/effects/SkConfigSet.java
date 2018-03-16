package com.github.kilosiberia.effects;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;
import com.github.kilosiberia.utils.SkConfigUtils;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SkConfigSet extends Effect {

	private Expression<String> path;
	private Expression<String> value;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.path = (Expression<String>) exprs[0];
		this.value = (Expression<String>) exprs[1];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "Set script's config";
	}

	@Override
	protected void execute(Event e) {
		try {
			FileConfiguration cfg = new SkConfigUtils(SkSiberities.getInstance()).getConfig();
			cfg.set(path.getSingle(e), value.getSingle(e));
		} catch (NullPointerException ex) {
			Bukkit.getLogger().info("SkSiberities was can not find config.");
			ex.printStackTrace();
		}
	}
}
