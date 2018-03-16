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
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibLine extends Effect {

	private Expression<?> start;
	private Expression<?> end;
	private Expression<String> id;
	private Expression<ParticleEffect> particle;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
		start = exp[0];
		end = exp[1];
		id = (Expression<String>) exp[2];
		particle = (Expression<ParticleEffect>) exp[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object startObject = start.getSingle(evt);
		Object endObject = end.getSingle(evt);
		LineEffect effect = new LineEffect(SkSiberities.effLibManager);
		if (startObject instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) startObject));
		} else if (startObject instanceof Location) {
			effect.setDynamicOrigin(new DynamicLocation((Location) startObject));
		} else {
			assert false;
		}
		if (endObject instanceof Entity) {
			effect.setDynamicTarget(new DynamicLocation((Entity) endObject));
		} else if (endObject instanceof Location) {
			effect.setDynamicTarget(new DynamicLocation((Location) endObject));
		} else {
			assert false;
		}
		if (particle != null) {
			effect.particle = particle.getSingle(evt);
		}
		effect.infinite();
		effect.start();
		Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
		if (!check) {
			effect.cancel();
		}
	}

}
