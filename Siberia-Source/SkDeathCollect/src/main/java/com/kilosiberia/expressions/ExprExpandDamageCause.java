package com.kilosiberia.expressions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;

public class ExprExpandDamageCause extends SimpleExpression<String> {

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!ScriptLoader.isCurrentEvent(PlayerDeathEvent.class)) {
			Skript.error
				("Cannot use 'expand damagecause' outside of a death event", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "expand damagecause";
	}

	protected String getExpDamageCause(Event e) {
		Player p = ((PlayerDeathEvent) e).getEntity();
		final String event = p.getLastDamageCause().getCause().name();
		if(event.equals("ENTITY_ATTACK")){
			return "VehicleExprosion";

		}
		return null;
	}



	@Override
	protected String[] get(Event event){
		return new String[]{getExpDamageCause(event)};
	}
}

