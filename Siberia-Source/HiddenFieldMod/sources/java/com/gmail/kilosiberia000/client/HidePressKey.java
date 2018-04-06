package com.gmail.kilosiberia000.client;

import org.lwjgl.input.Keyboard;

import com.gmail.kilosiberia000.HiddenFieldMod;
import com.gmail.kilosiberia000.common.RegisterKeyInputs;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class HidePressKey {


	@SubscribeEvent
	public void PressKeyEvent(KeyInputEvent e){
		if(RegisterKeyInputs.skriptKeys != null){
			for(Integer key : RegisterKeyInputs.skriptKeys){
				if(Keyboard.isKeyDown(key)){
					byte[] bte = new String("keys|send," + Keyboard.getKeyName(key)).getBytes();
					ByteBuf buf = Unpooled.wrappedBuffer(bte);
					FMLProxyPacket sendpkt = new FMLProxyPacket(buf, "SkSiberities");
					HiddenFieldMod.channel.sendToServer(sendpkt);
				}
			}
		}
	}
}
