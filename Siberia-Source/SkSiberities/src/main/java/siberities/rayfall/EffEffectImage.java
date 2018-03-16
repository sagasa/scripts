package siberities.rayfall;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.github.kilosiberia.SkSiberities;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.effect.ImageEffect;
import de.slikey.effectlib.util.DynamicLocation;

public class EffEffectImage extends Effect {

	Expression<String> image;
	Expression<?> target;
	Expression<String> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
		image = (Expression<String>) exp[0];
		target = exp[1];
		id = (Expression<String>) exp[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		ImageEffect effect = null;
		try {
			effect = new ImageEffect(SkSiberities.effLibManager);
			File image = new File(id.getSingle(evt).replace("\"", ""));
			effect.loadFile(image);
		} catch (IOException exception) {
			exception.printStackTrace();
			assert false;
		}
		if (tar instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
		} else if (tar instanceof Location) {
			effect.setDynamicOrigin(new DynamicLocation((Location) tar));
		} else {
			assert false;
		}
		Boolean check = new EffLibManager().setEffect(effect, id.getSingle(evt).replace("\"", ""));
		if (!check) {
			effect.cancel();
		}

	}

}
