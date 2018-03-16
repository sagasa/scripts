package com.github.kilosiberia.BukkitEvent;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.kilosiberia.types.SPacket;

public class SiberitiesPacketEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	private SPacket packet;

	public SiberitiesPacketEvent(SPacket packet) {
		this.packet = packet;
	}

	public SPacket getPacket() {
		return this.packet;
	}

}
