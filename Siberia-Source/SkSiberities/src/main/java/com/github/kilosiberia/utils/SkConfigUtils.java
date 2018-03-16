package com.github.kilosiberia.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SkConfigUtils {

	private FileConfiguration config = null;
	private final File configFile;
	private final String file;
	private final Plugin plugin;

	public SkConfigUtils(Plugin plugin) {
		this(plugin, "scriptConfig");
	}

	public SkConfigUtils(Plugin plugin, String configName) {
		this.plugin = plugin;
		this.file = configName + ".yml";
		configFile = new File(plugin.getDataFolder(), file);
	}

	public void saveDefaultConfig() {
		plugin.saveResource(file, false);
	}

	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(configFile);
	}

	public FileConfiguration getConfig() {
		if (config == null) {
			reloadConfig();
		}
		return config;
	}

	public void saveConfig() {
		if (config == null) {
			return;
		}
		try {
			getConfig().save(configFile);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "SkSiberities was could not save config to " + configFile, ex);
		}
	}
}
