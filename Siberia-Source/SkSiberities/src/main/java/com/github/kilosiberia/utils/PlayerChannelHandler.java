package com.github.kilosiberia.utils;

import net.minecraft.util.io.netty.channel.ChannelDuplexHandler;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelPromise;

public class PlayerChannelHandler extends ChannelDuplexHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInTabComplete")) {
			String s = (String) ReflectionUtils.getFieldValue(msg, "a");
			if (!(s.startsWith("/ver") || s.startsWith("/version"))) {
				super.channelRead(ctx, msg);
			}
		} else {
			super.channelRead(ctx, msg);
		}
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}
}
