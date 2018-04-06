package com.gmail.kilosiberia000.server;

import java.io.File;
import java.util.Arrays;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;

public class ModConfig {

	private static String keys;
	private static Configuration cfg;

	public static void init(File f){

		cfg = new Configuration(f);

		try{
			cfg.load();
			keys = cfg.getString("getKeyList", "Keys", "56, 184", "http://forum.minecraftuser.jp/viewtopic.php?t=9510 Do not write [KEY].");
			FMLLog.getLogger().info("Loaded Keys:" + keys);

		}finally{
			cfg.save();
		}
	}

	public static void reload(){
		try{
			cfg.load();
			keys = cfg.getString("getKeyList", "Keys", "56, 184", "http://forum.minecraftuser.jp/viewtopic.php?t=9510 Do not write [KEY].");
			FMLLog.getLogger().info("Loaded Keys:" + keys);
			new OnConfigLoadHandler().onConfigChange();

		}finally{
			cfg.save();
		}
	}

	public String getKey(){
		return keys;
	}

	public static String[] formatString(){
		if(keys != null){
			keys = keys.replace(" ", "");
			String[] str = keys.split(",");
			FMLLog.getLogger().info("Splited from config. key name is :" + Arrays.toString(str));
			return str;
		}
		return null;
	}
}
