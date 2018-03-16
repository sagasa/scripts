package com.github.kilosiberia.expressions;

import com.github.kilosiberia.types.SPacket;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprMessageOfSPacket extends SimplePropertyExpression<SPacket, String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	protected String getPropertyName() {
		return "message of %spacket%";
	}

	@Override
	public String convert(SPacket f) {
		return f.getEncodedMessage();
	}

}
