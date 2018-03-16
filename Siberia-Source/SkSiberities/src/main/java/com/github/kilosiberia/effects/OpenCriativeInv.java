package com.github.kilosiberia.effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class OpenCriativeInv extends Effect {

	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.player = (Expression<Player>) exprs[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "Open creative inventory to %player%";
	}

	@Override
	protected void execute(Event e) {
		Player p = player.getSingle(e);

		Inventory inv = Bukkit.createInventory(p, InventoryType.CREATIVE);
		p.openInventory(inv);
	}

}
