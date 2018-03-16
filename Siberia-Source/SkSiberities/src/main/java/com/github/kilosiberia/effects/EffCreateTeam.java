package com.github.kilosiberia.effects;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

import com.github.kilosiberia.utils.ScoreboardUtils;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCreateTeam extends Effect {

	private Expression<String> str;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.str = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "create team [named] %string%";
	}

	@Override
	protected void execute(Event e) {
		Scoreboard board = ScoreboardUtils.getBoard();
		board.registerNewTeam(str.getSingle(e));
	}

}
