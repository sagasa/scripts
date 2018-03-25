package com.github.kilosiberia.expressions;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.utils.ScoreboardUtils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprMemberofTeam extends SimpleExpression<OfflinePlayer> {

	private Expression<String> str;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return Player.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.str = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "number of team %string%";
	}

	@Override
	protected OfflinePlayer[] get(Event e) {
		Team team = ScoreboardUtils.getBoard().getTeam(str.getSingle(e));
		if(team == null){
			return null;
		}
		OfflinePlayer[] players = new OfflinePlayer[team.getPlayers().size()];

		int count = 0;
		for(OfflinePlayer player : team.getPlayers()){
			players[count] = player;
			count++;
		}
		return players;
	}
}
