package com.github.kilosiberia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.github.kilosiberia.BukkitEvent.SiberitiesPacketEvent;
import com.github.kilosiberia.types.SPacket;
import com.github.kilosiberia.utils.Register;
import com.github.kilosiberia.utils.RegisterElements;
import com.github.kilosiberia.utils.SkConfigUtils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptEventInfo;
import de.slikey.effectlib.EffectManager;
import siberities.rayfall.EffLibManager;
import siberities.sagasa_1500.GzipMain;

public class SkSiberities extends JavaPlugin implements PluginMessageListener {

	private static SkSiberities plugin;
	public static ArrayList<Object[]> ena = new ArrayList<>();
	public static ArrayList<String> enumNames = new ArrayList<>();
	public static ArrayList<Class<?>> enumClasses = new ArrayList<>();
	public static EffectManager effLibManager;

	private SkConfigUtils CU = new SkConfigUtils(this);
	private String ping;
	private String hash;
	private String mods;

	private FileReader reader = null;
	private BufferedReader buf = null;

	public void onEnable() {
		getLogger().info("Enabling SkSiberities");

		Skript.registerAddon(this);

		// SkClassLoader.Load();
		// Bukkit.getLogger().info("SkSiberities Class Loaded.");

		// SkSiberities.newEvent(OnInventoryChange.class,
		// InventoryChangeEvent.class,
		// "Inventory Change",
		// "[on] inventory change[d]");
		//
		//
		// Skript.registerExpression
		// (ChangedSlot.class, Integer.class, ExpressionType.SIMPLE, "change[d][
		// ]slot");
		//
		// Skript.registerExpression
		// (PickingItem.class, ItemStack.class, ExpressionType.SIMPLE,
		// "pick[ing][ ]item");

		new GzipMain();
		new RegisterElements();
		Register.register();

		this.saveDefaultConfig();

		getLogger().info("Loaded Config.");

		plugin = this;
		effLibManager = new EffectManager(this);

		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "SkSiberities");
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "SkSiberities", this);
	}

	public void onDisable() {
		if (buf != null) {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		new EffLibManager().dumpData();
	}

	public static SkSiberities getInstance() {
		return plugin;
	}

	public static <E extends SkriptEvent> SkriptEventInfo<E> newEvent(Class<E> evt, Class<? extends Event> event,
			String name, String... syntaxes) {
		return newEvent(evt, event, 1, name, syntaxes);
	}

	public static <E extends SkriptEvent> SkriptEventInfo<E> newEvent(Class<E> evt, Class<? extends Event> event,
			int amount, String name, String... syntaxes) {
		if (Skript.isAcceptRegistrations()) {
			return Skript.registerEvent(name, evt, event, syntaxes);
		}
		return null;
	}

	public SkConfigUtils getCU() {
		return CU;
	}

	public String getPing() {
		return ping;
	}

	public String getHash() {
		return hash;
	}

	public String getModList() {
		return mods;
	}

	public void sendPacket(Player p, String channel, byte[] message) {
		p.sendPluginMessage(this, channel, message);
	}

	public String readFile(File file, String name) {
		if (file.exists()) {
			try {
				reader = new FileReader(file);
				buf = new BufferedReader(reader);
				String str = null;
				while ((str = buf.readLine()) != null) {
					if (str.contains(name)) {
						this.ping = str.split(":")[1];
						return ping;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "-1";
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] data) {
		Bukkit.getPluginManager().callEvent(new SiberitiesPacketEvent(new SPacket(channel, data, player)));
		if (channel.equals("SkSiberities")) {
			try {
				String str = new String(data, "UTF-8");
				String[] split = str.split(",");

				if (split[0].equals("ping|send")) {
					this.ping = split[1];

				} else if (split[0].equals("hash|send")) {
					this.hash = split[1];

				} else if (split[0].equals("mods|send")) {
					this.mods = split[1];
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

}
