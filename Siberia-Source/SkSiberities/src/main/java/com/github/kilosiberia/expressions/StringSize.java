package com.github.kilosiberia.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class StringSize extends SimplePropertyExpression<String, Number> {

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	protected String getPropertyName() {
		return "String size";
	}

	@Override
	public Number convert(String f) {
		Number size = (f == null) ? 0 : f.length();
		return size;
	}

}
