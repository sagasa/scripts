package com.gmail.kilosiberia000.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class HideIMessageClient implements IMessage{

	public Integer in;

	public HideIMessageClient(){}

	public HideIMessageClient(Integer in){
		this.in = in;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.in = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.in);
	}

}
