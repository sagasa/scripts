package com.kilosiberia.expressions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;

public class ExprExpandAttacker extends SimpleExpression<Player>{



	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Player> getReturnType() {
		return Player.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!ScriptLoader.isCurrentEvent(PlayerDeathEvent.class, EntityDamageByEntityEvent.class)) {
			Skript.error
				("Cannot use 'expand attacker' outside of a death event", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "expand attacker";
	}

	protected Player getAttacker(Event e) {
		Entity p = ((EntityDamageByEntityEvent) e).getEntity();
		if(p instanceof Player){
			final Entity attacker = ((Player) p);
			if(attacker instanceof Player){
				return (Player) attacker;
			}
		}else if(p instanceof Vehicle){
			final Entity attacker = ((Player) p).getKiller();
			if(attacker instanceof Vehicle){
				if(p.getPassenger() instanceof Player){
					return (Player) attacker;
				}
			}

		}
		return null;
	}



	@Override
	protected Player[] get(Event event){
		return new Player[]{getAttacker(event)};

	}

}
