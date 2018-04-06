package com.gmail.kilosiberia000.common;

import java.util.ArrayList;
import java.util.List;

import com.gmail.kilosiberia000.server.ModConfig;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RegisterKeyInputs {

	public static List<Integer> skriptKeys = new ArrayList<Integer>();

	@SideOnly(Side.SERVER)
	public static void register(){
		if(ModConfig.formatString() == null) {return;}
		skriptKeys.clear();
		for(String str : ModConfig.formatString()){
			FMLLog.getLogger().info("Getting Key Index " + str);
			try{
				if(Integer.parseInt(str) > 256){
					FMLLog.getLogger().warn(str + " is not key number.");
					continue;
				}
				skriptKeys.add(Integer.parseInt(str));
				FMLLog.getLogger().info("Skript keys added key of " +  str);
			}catch(Exception e){
				e.printStackTrace();
				FMLLog.getLogger().error(str + "is not number.");
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public static void join(List<Integer> key){
		skriptKeys.clear();
		skriptKeys.addAll(key);
	}


}
