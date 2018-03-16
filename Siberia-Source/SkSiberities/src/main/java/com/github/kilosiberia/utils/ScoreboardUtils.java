package com.github.kilosiberia.utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardUtils {

	public ScoreboardUtils() {
	}

	public static Scoreboard getBoard() {
		return Bukkit.getScoreboardManager().getMainScoreboard();
	}
}
