package com.gmail.kilosiberia000.client;

import java.util.ArrayList;
import java.util.List;

import com.gmail.kilosiberia000.common.RegisterKeyInputs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HideIMessageHandlerClient implements IMessageHandler<HideIMessageClient, IMessage>{

	private List<Integer> list = new ArrayList<Integer>();

	@Override
	public IMessage onMessage(HideIMessageClient message, MessageContext ctx){

		if(message.in.compareTo(1000) == 0){
			RegisterKeyInputs.join(list);
			this.list.clear();
		}else{
			this.list.add(message.in);
		}
		return null;
	}

}
