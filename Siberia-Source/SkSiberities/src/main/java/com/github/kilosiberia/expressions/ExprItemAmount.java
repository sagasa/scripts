package com.github.kilosiberia.expressions;

import org.bukkit.inventory.ItemStack;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprItemAmount extends SimplePropertyExpression<ItemStack, Number> {

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	protected String getPropertyName() {
		return "(amount|size|number) of item %itemstack%";
	}

	@Override
	public Number convert(ItemStack f) {
		return f.getAmount();
	}

}
