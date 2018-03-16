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
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibCircle extends Effect {

	private Expression<?> target;
	private Expression<String> id;
	private Expression<ParticleEffect> particle;
	private Expression<Number> rad;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
		target = exp[0];
		id = (Expression<String>) exp[1];
		particle = (Expression<ParticleEffect>) exp[2];
		rad = (Expression<Number>) exp[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		CircleEffect effect = new CircleEffect(SkSiberities.effLibManager);
		if (tar instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
		} else if (tar instanceof Location) {
			effect.setDynamicOrigin(new DynamicLocation((Location) tar));
		} else {
			assert false;
		}
		if (particle != null) {
			effect.particle = particle.getSingle(evt);
		}
		if (rad != null) {
			effect.radius = rad.getSingle(evt).floatValue();
		}
		effect.infinite();
		effect.start();
		Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
		if (!check) {
			effect.cancel();
		}

	}
}
