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
import de.slikey.effectlib.effect.TornadoEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibTornado extends Effect {
	private Expression<?> target;
	private Expression<String> id;
	private Expression<ParticleEffect> torParticle;
	private Expression<ParticleEffect> cloudParticle;
	private Expression<Number> radius;
	private Expression<Number> height;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
		target = exp[0];
		id = (Expression<String>) exp[1];
		torParticle = (Expression<ParticleEffect>) exp[2];
		cloudParticle = (Expression<ParticleEffect>) exp[3];
		radius = (Expression<Number>) exp[4];
		height = (Expression<Number>) exp[5];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		TornadoEffect effect = new TornadoEffect(SkSiberities.effLibManager);
		if (tar instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
			if (torParticle != null) {
				effect.tornadoParticle = torParticle.getSingle(evt);
			}
			if (cloudParticle != null) {
				effect.cloudParticle = cloudParticle.getSingle(evt);
			}
			if (radius.getSingle(evt) != null) {
				effect.maxTornadoRadius = radius.getSingle(evt).floatValue();
			}
			if (height.getSingle(evt) != null) {
				effect.tornadoHeight = height.getSingle(evt).floatValue();
			}
			effect.infinite();
			effect.start();
			Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
			if (!check) {
				effect.cancel();
			}
		} else if (tar instanceof Location) {
			effect.setDynamicOrigin(new DynamicLocation((Location) tar));
			if (torParticle != null) {
				effect.tornadoParticle = torParticle.getSingle(evt);
			}
			if (cloudParticle != null) {
				effect.cloudParticle = cloudParticle.getSingle(evt);
			}
			if (radius.getSingle(evt) != null) {
				effect.maxTornadoRadius = radius.getSingle(evt).floatValue();
			}
			if (height.getSingle(evt) != null) {
				effect.tornadoHeight = height.getSingle(evt).floatValue();
			}
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
