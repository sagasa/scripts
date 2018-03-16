package com.github.kilosiberia.types;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;

import org.bukkit.entity.Player;

import ch.njol.skript.variables.Variables;
import ch.njol.yggdrasil.Fields;
import ch.njol.yggdrasil.YggdrasilSerializable.YggdrasilExtendedSerializable;

public class SPacket implements YggdrasilExtendedSerializable {

	private String channel;
	private byte[] message;
	private String string;
	private String title;
	private String value;
	private Player player;

	public SPacket(String channel, byte[] message, Player player) {

		this.channel = channel;
		this.message = message;
		this.player = player;

		try {
			this.string = new String(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			this.string = "error";
		}

		String[] split = string.split(",");

		if (split[0].equals("ping|send")) {
			this.title = "ping";

		} else if (split[0].equals("hash|send")) {
			this.title = "hash";

		} else if (split[0].equals("mods|send")) {
			this.title = "mods";
		}

		this.value = split[1];
	}

	public String getChannel() {
		return channel;
	}

	public String getEncodedMessage() {
		return string;
	}

	public String getTitle() {
		return title;
	}

	public String getValue() {
		return value;
	}

	public Player getPlayer() {
		return player;
	}

	public byte[] getMessage() {
		return message;
	}

	public String toString() {
		return "channel: " + channel + " message: " + getEncodedMessage() + " player: " + player.getName();
	}

	@Override
	public void deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
		channel = f.getAndRemoveObject("channel", String.class);
		message = f.getAndRemoveObject("message", byte[].class);
		player = f.getAndRemoveObject("player", Player.class);

		f.setFields(this, Variables.yggdrasil);
	}

	@Override
	public Fields serialize() throws NotSerializableException {
		final Fields f = new Fields(this);
		f.putObject("channel", channel);
		f.putObject("message", message);
		f.putObject("player", player);
		return f;
	}
}
