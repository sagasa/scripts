package com.github.kilosiberia.expressions;

import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprModList extends SimpleExpression<String> {

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "mod list of last packet";
	}

	@Override
	protected String[] get(Event e) {
		String[] str = SkSiberities.getInstance().getModList().split("]");
		return str == null ? new String[] { "<none>" } : str;
	}

}
