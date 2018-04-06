package com.gmail.kilosiberia000;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.gmail.kilosiberia000.client.HidePressKey;
import com.gmail.kilosiberia000.common.CommonProxy;
import com.gmail.kilosiberia000.common.HideCommand;
import com.gmail.kilosiberia000.common.HideIMessage;
import com.gmail.kilosiberia000.common.RegisterKeyInputs;
import com.gmail.kilosiberia000.server.ModConfig;
import com.gmail.kilosiberia000.server.ServerJoinInit;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

@Mod(modid = HiddenFieldMod.ModID, version = HiddenFieldMod.Version)
public class HiddenFieldMod {
	public static final String ModID = "HiddenFieldMod";
	public static final String Version = "0.0.6";
	public static final FMLEventChannel channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("SkSiberities");
	public static final SimpleNetworkWrapper modChannel = NetworkRegistry.INSTANCE.newSimpleChannel("HiddenFieldMod");
	@SidedProxy(clientSide = "com.gmail.kilosiberia000.client.ClientProxy", serverSide = "com.gmail.kilosiberia000.common.CommonProxy")
	public static CommonProxy proxy;

	public static HiddenFieldMod getInstance(){
		return new HiddenFieldMod();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		if(e.getSide().isServer()){
			ModConfig.init(e.getSuggestedConfigurationFile());
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent e){
		channel.register(this);
		proxy.preInit();

		if(e.getSide().isClient())
			FMLCommonHandler.instance().bus().register(new HidePressKey());
		else if(e.getSide().isServer()){
			FMLCommonHandler.instance().bus().register(new ServerJoinInit());
			RegisterKeyInputs.register();
		}

	}

	@EventHandler
	public void load(FMLServerStartingEvent e){
		e.registerServerCommand(new HideCommand());
	}

	@SubscribeEvent
    public void onClientPacket(ClientCustomPacketEvent event) throws UnsupportedEncodingException {

        Packet packet = event.packet.toC17Packet();

        if (packet instanceof C17PacketCustomPayload){
            C17PacketCustomPayload pluginmessage = (C17PacketCustomPayload) packet;
            String message = new String(pluginmessage.func_149558_e(), "UTF-8");
            String[] split = message.split(",");

            if(split[0].equals("ping")){
	    		modChannel.sendToServer(new HideIMessage(1));

            }else if(split[0].equals("ping|send")){
            	C17PacketCustomPayload pkt = (C17PacketCustomPayload) event.packet.toC17Packet();
            	FMLProxyPacket sendpkt = new FMLProxyPacket(pkt);
            	channel.sendToServer(sendpkt);


            }else if(split[0].equals("hash")){
            	String str = "hash|send," + getFlansPackHash();
            	byte[] sendmessage = str.getBytes();
            	ByteBuf data = Unpooled.wrappedBuffer(sendmessage);
            	C17PacketCustomPayload pkt = new C17PacketCustomPayload("SkSiberities", data);
            	FMLProxyPacket sendpkt = new FMLProxyPacket(pkt);
            	channel.sendToServer(sendpkt);


            }else if(split[0].equals("mods")){

            	List<ModContainer> rawmods = Loader.instance().getModList();

            	StringBuilder builder = new StringBuilder();
            	builder.append("mods|send").append(",");

            	for(ModContainer mod : rawmods){
            		builder.append(mod.getName()).append("]");
            	}

            	String str = builder.substring(0, builder.length() - 1);

            	byte[] sendmessage = str.getBytes();
            	ByteBuf data = Unpooled.wrappedBuffer(sendmessage);

            	C17PacketCustomPayload pkt = new C17PacketCustomPayload("SkSiberities", data);
            	FMLProxyPacket sendpkt = new FMLProxyPacket(pkt);

            	channel.sendToServer(sendpkt);
            }
        }
	}

	@SideOnly(Side.CLIENT)
	private String getFlansPackHash(){

		File folder = getFlansFolder();

		if(folder != null){

			try {

				File[] files = folder.listFiles();
				File file = null;
				for(File value : files){
					if (value.getName().contains("HiddenField"))
						file = value;
				}

				if(file != null)
					return DigestUtils.sha256Hex(new FileInputStream(file));

				else
					return "";

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	@SideOnly(Side.CLIENT)
	private File getFlansFolder(){
		File file = new File(Minecraft.getMinecraft().mcDataDir, "Flan");
		if(file.exists()){
			return file;
		}
		return null;
	}



}