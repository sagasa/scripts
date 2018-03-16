package siberities.rayfall;

import java.util.HashMap;

import org.bukkit.plugin.Plugin;

import de.slikey.effectlib.Effect;

public class EffLibManager {

	public EffLibManager() {
	}

	public static EffLibManager getInstance() {
		return new EffLibManager();
	}

	private HashMap<String, Effect> effectMap = new HashMap<String, Effect>();
	@SuppressWarnings("unused")
	private Plugin plugin;

	public void dumpData() {
		for (Effect e : effectMap.values()) {
			e.cancel();
		}
	}

	public boolean setEffect(Effect effect, String id) {
		if (effectMap.containsKey(id)) {
			return false;
		} else {
			effectMap.put(id, effect);
			return true;
		}
	}

	public void deleteEffect(String id) {
		if (effectMap.containsKey(id)) {
			Effect effect = effectMap.get(id);
			effect.cancel();
			effectMap.remove(id);
		}
	}
}
