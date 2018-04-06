package com.gmail.kilosiberia000.common;

import java.io.UnsupportedEncodingException;

import com.gmail.kilosiberia000.HiddenFieldMod;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class HideIMessageHandler implements IMessageHandler<HideIMessage, IMessage>{

	@Override
	public IMessage onMessage(HideIMessage message, MessageContext ctx) {
		if(MinecraftServer.getServer() != null){

			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			int ping = player.ping;
			HiddenFieldMod mod = HiddenFieldMod.getInstance();

			byte[] sendmessage;

			try {
				sendmessage = new String("ping|send," + ping).getBytes("UTF-8");
				ByteBuf data = Unpooled.wrappedBuffer(sendmessage);
				FMLProxyPacket packet = new FMLProxyPacket(data , "SkSiberities");
				packet.setTarget(Side.CLIENT);
				mod.channel.sendTo(packet, player);

			} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
			}
		}
		return null;
	}
}
