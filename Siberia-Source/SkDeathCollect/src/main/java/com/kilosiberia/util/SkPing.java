//package com.kilosiberia.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.scheduler.BukkitRunnable;
//
//import com.kilosiberia.SkDeathCollect;
//
//public class SkPing implements Listener {
//
//
//	public static SkDeathCollect plugin;
//
//	@SuppressWarnings("static-access")
//	public SkPing(SkDeathCollect plugin){
//		this.plugin = plugin;
//	}
//
//	@EventHandler
//	public void onJoin(PlayerJoinEvent e){
//		Player p = e.getPlayer();
//		setPing(p);
//	}
//
//	private void setPing(Player p){
//
//		String[] cmd = {"ping", p.getAddress().getHostName(), "-c", "1"};
//		try {
//			final Process proc = new ProcessBuilder(cmd).start();
//			new BukkitRunnable(){
//				public void run(){
//					try {
//						BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//						System.out.println(br.readLine());
//						plugin.getConfig().set("Ping." + p.getName() , br.readLine());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}.runTaskLater(plugin, 10);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static int getPing(Player p){
//		return plugin.getConfig().getInt("Ping." + p.getName());
//
//	}
//}
