package com.gmail.kilosiberia000.client;

import com.gmail.kilosiberia000.HiddenFieldMod;
import com.gmail.kilosiberia000.common.CommonProxy;

import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerClientInfo(){

	}

	public void preInit(){
		HiddenFieldMod.modChannel.registerMessage(HideIMessageHandlerClient.class, HideIMessageClient.class, 0, Side.CLIENT);
	}
}
