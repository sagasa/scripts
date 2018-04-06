package com.gmail.kilosiberia000.server;

import com.gmail.kilosiberia000.HiddenFieldMod;
import com.gmail.kilosiberia000.common.HideIMessage;
import com.gmail.kilosiberia000.common.RegisterKeyInputs;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerJoinInit {

	@SubscribeEvent
	public void onServerJoin(PlayerLoggedInEvent e){
		for(Integer in : RegisterKeyInputs.skriptKeys){
			HiddenFieldMod.modChannel.sendTo(new HideIMessage(in), (EntityPlayerMP) e.player);
		}
		HiddenFieldMod.modChannel.sendToAll(new HideIMessage(1000));
		FMLLog.getLogger().info("Send handle keys for " + e.player.getDisplayName());
	}
}
