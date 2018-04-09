package com.gmail.kilosiberia000.common;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class HideIMessage implements IMessage {

	public int in;

	public HideIMessage(){}

	public HideIMessage(Integer in){
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
