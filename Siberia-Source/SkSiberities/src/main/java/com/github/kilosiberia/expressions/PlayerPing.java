package com.github.kilosiberia.expressions;

import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class PlayerPing extends SimpleExpression<Integer> {

	private String ping;

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "ping of last packet";
	}

	@Override
	protected Integer[] get(Event e) {
		this.ping = SkSiberities.getInstance().getPing();
		return ping == null ? new Integer[] { -1 } : new Integer[] { Integer.parseInt(ping) };
	}
}