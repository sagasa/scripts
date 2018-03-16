package com.github.kilosiberia.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSendPacketPing extends Effect {

	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.player = (Expression<Player>) exprs[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "send ping packet to %player%";
	}

	@Override
	protected void execute(Event e) {
		SkSiberities.getInstance().sendPacket(player.getSingle(e), "SkSiberities",
				new String("ping," + player.getSingle(e).getName()).getBytes());
	}

}
