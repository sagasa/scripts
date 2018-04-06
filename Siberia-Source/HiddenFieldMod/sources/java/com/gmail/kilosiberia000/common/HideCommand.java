package com.gmail.kilosiberia000.common;

import com.gmail.kilosiberia000.server.ModConfig;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class HideCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "hiddenfieldmod";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "[OP]/hiddenfieldmod reload";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(sender.getEntityWorld().isRemote){
			sender.addChatMessage(new ChatComponentText("Not processing on Client side."));
		}
		if(args.length < 2){
			if(args.length == 0){
				sender.addChatMessage(new ChatComponentText("[OP]/hiddenfieldmod reload"));
			}else{
				if(args[0].equals("reload")){
					ModConfig.reload();
					sender.addChatMessage(new ChatComponentText("Reloaded HiddenfieldMod config."));;
				}
			}
		}

	}

	@Override
	public int getRequiredPermissionLevel(){
		return 4;
	}

}
