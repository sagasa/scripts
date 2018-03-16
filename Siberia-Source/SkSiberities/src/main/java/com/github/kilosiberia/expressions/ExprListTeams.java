package com.github.kilosiberia.expressions;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.utils.ScoreboardUtils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprListTeams extends SimpleExpression<Team> {

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Team> getReturnType() {
		return Team.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "list of teams";
	}

	@Override
	protected Team[] get(Event e) {
		Team[] team = new Team[ScoreboardUtils.getBoard().getTeams().size()];
		return ScoreboardUtils.getBoard().getTeams().toArray(team);
	}
}
