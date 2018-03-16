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
import de.slikey.effectlib.effect.AnimatedBallEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibAnimatedBallEffect extends Effect {

	private Expression<?> target;
	private Expression<String> id;
	private Expression<ParticleEffect> particle;
	private Expression<Number> xoffset;
	private Expression<Number> yoffset;
	private Expression<Number> zoffset;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
		target = exp[0];
		id = (Expression<String>) exp[1];
		particle = (Expression<ParticleEffect>) exp[2];
		xoffset = (Expression<Number>) exp[3];
		yoffset = (Expression<Number>) exp[4];
		zoffset = (Expression<Number>) exp[5];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		AnimatedBallEffect effect = new AnimatedBallEffect(SkSiberities.effLibManager);
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
		if (xoffset != null && yoffset != null && zoffset != null) {
			effect.xOffset = xoffset.getSingle(evt).floatValue();
			effect.yOffset = yoffset.getSingle(evt).floatValue();
			effect.zOffset = zoffset.getSingle(evt).floatValue();
		}
		effect.infinite();
		effect.start();
		Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
		if (!check) {
			effect.cancel();
		}

	}
}
