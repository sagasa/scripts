package siberities.rayfall;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.effect.BigBangEffect;
import de.slikey.effectlib.util.DynamicLocation;

public class EffBigBangEffect extends Effect {
	private Expression<?> target;
	private Expression<String> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
		target = exp[0];
		id = (Expression<String>) exp[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		BigBangEffect effect = new BigBangEffect(SkSiberities.effLibManager);
		if (tar instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
			effect.infinite();
			effect.start();
			Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
			if (!check) {
				effect.cancel();
			}
		} else if (tar instanceof Location) {
			effect.setDynamicOrigin(new DynamicLocation((Location) tar));
			effect.infinite();
			effect.start();
			Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
			if (!check) {
				effect.cancel();
			}
		} else {
			assert false;
		}
	}

}
