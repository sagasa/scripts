package com.gmail.kilosiberia000.server;

import com.gmail.kilosiberia000.HiddenFieldMod;
import com.gmail.kilosiberia000.common.HideIMessage;
import com.gmail.kilosiberia000.common.RegisterKeyInputs;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OnConfigLoadHandler {

	@SideOnly(Side.SERVER)
	protected void onConfigChange(){
		RegisterKeyInputs.register();
		for(Integer in : RegisterKeyInputs.skriptKeys){
			HiddenFieldMod.modChannel.sendToAll(new HideIMessage(in));
		}
		HiddenFieldMod.modChannel.sendToAll(new HideIMessage(1000));
		FMLLog.getLogger().info("Handling key inputs was changed.");
	}
}
