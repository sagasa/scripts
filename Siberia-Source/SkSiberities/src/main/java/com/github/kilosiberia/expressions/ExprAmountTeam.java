package com.github.kilosiberia.expressions;

import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.utils.ScoreboardUtils;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprAmountTeam extends SimplePropertyExpression<String, Number> {

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	protected String getPropertyName() {
		return "number of team %string%";
	}

	@Override
	public Number convert(String f) {
		Team team;
		return (team = ScoreboardUtils.getBoard().getTeam(f)) == null ? 0 : team.getSize();
	}

}
