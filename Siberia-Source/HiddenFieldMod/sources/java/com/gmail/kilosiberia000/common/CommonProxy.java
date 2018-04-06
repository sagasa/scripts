package com.gmail.kilosiberia000.common;

import com.gmail.kilosiberia000.HiddenFieldMod;

import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	public void registerClientInfo(){

	}

	public void preInit(){
		HiddenFieldMod.modChannel.registerMessage(HideIMessageHandler.class, HideIMessage.class, 0, Side.SERVER);
	}
}
