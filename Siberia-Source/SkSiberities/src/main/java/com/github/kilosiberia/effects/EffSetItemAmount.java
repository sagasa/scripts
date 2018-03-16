package com.github.kilosiberia.effects;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetItemAmount extends Effect {

	private Expression<ItemStack> item;
	private Expression<Number> amount;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.item = (Expression<ItemStack>) exprs[0];
		this.amount = (Expression<Number>) exprs[1];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "set (amount|number|size) [of] item %itemstack% to %number%";
	}

	@Override
	protected void execute(Event e) {
		if (item != null)
			item.getSingle(e).setAmount(amount.getSingle(e).intValue());
	}
}
