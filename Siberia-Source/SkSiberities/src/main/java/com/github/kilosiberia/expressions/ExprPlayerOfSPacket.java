package com.github.kilosiberia.expressions;

import org.bukkit.entity.Player;

import com.github.kilosiberia.types.SPacket;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprPlayerOfSPacket extends SimplePropertyExpression<SPacket, Player> {

	@Override
	public Class<? extends Player> getReturnType() {
		return Player.class;
	}

	@Override
	protected String getPropertyName() {
		return "player of %spakcet%";
	}

	@Override
	public Player convert(SPacket f) {
		return f.getPlayer();
	}

}
