package com.github.kilosiberia.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.utils.ScoreboardUtils;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffRemovePlayerTeam extends Effect {

	private Expression<String> str;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.player = (Expression<Player>) exprs[0];
		this.str = (Expression<String>) exprs[1];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "remove %player% from team %string%";
	}

	@Override
	protected void execute(Event e) {
		Scoreboard board = ScoreboardUtils.getBoard();
		Team team;
		Player pl = player.getSingle(e);
		if ((team = board.getTeam(str.getSingle(e))) != null) {
			if (!(team.hasPlayer(pl))) {
				team.removePlayer(pl);
			}
		}
	}

}
