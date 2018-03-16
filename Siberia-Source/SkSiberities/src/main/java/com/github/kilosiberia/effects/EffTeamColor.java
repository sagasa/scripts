package com.github.kilosiberia.effects;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.utils.ScoreboardUtils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffTeamColor extends Effect {

	private Expression<String> color;
	private Expression<String> team;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		try {
			ChatColor.valueOf(exprs[0].toString().replace("\"", "").trim().replace(" ", "_").toUpperCase());
			this.color = (Expression<String>) exprs[1];
			this.team = (Expression<String>) exprs[0];
			return true;

		} catch (IllegalArgumentException e) {
			Skript.error(exprs[0].toString().replace("\"", "") + "is not color. You can use color is "
					+ Arrays.toString(ChatColor.values()));
			return false;

		} catch (NullPointerException e) {
			Skript.error(exprs[0].toString().replace("\"", "") + "is not color. You can use color is"
					+ Arrays.toString(ChatColor.values()));
			return false;
		}
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "set [color] %string% to team %string%";
	}

	@Override
	protected void execute(Event e) {
		Team team = ScoreboardUtils.getBoard().getTeam(this.team.getSingle(e));
		if (team != null) {
			team.setDisplayName(ChatColor.valueOf(color.getSingle(e).trim().replace(" ", "_").toUpperCase())
					+ team.getDisplayName());
			team.setPrefix(ChatColor.valueOf(color.getSingle(e).trim().replace(" ", "_").toUpperCase()) + "");
		}
	}

}
