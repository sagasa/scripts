package com.github.kilosiberia.expressions;

import com.github.kilosiberia.types.SPacket;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprSPacketValue extends SimplePropertyExpression<SPacket, String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	protected String getPropertyName() {
		return "value of %spacket%";
	}

	@Override
	public String convert(SPacket f) {
		return f.getValue();
	}

}
