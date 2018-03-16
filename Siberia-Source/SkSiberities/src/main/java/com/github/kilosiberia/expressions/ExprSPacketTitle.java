package com.github.kilosiberia.expressions;

import com.github.kilosiberia.types.SPacket;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprSPacketTitle extends SimplePropertyExpression<SPacket, String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	protected String getPropertyName() {
		return "title of %spacket%";
	}

	@Override
	public String convert(SPacket f) {
		return f.getTitle();
	}

}
