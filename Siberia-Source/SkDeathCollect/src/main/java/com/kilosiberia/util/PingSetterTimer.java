//package com.kilosiberia.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.bukkit.Bukkit;
//import org.bukkit.entity.Player;
//import org.bukkit.scheduler.BukkitRunnable;
//
//import com.kilosiberia.SkDeathCollect;
//
//public class PingSetterTimer extends BukkitRunnable {
//
//	public static SkDeathCollect plugin;
//
//	@SuppressWarnings("static-access")
//	public PingSetterTimer(SkDeathCollect plugin){
//		this.plugin = plugin;
//	}
//
//	@Override
//	public void run() {
//		if(!(Bukkit.getOnlinePlayers() == null)){
//			for(Player p : Bukkit.getOnlinePlayers()){
//				String[] cmd = {"ping", p.getAddress().getHostName(), "-c", "1"};
//				try {
//					final Process proc = new ProcessBuilder(cmd).start();
//					new BukkitRunnable(){
//						public void run(){
//							try {
//								BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//								System.out.println(br.readLine());
//								plugin.getConfig().set("Ping." + p.getName() , br.readLine());
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}
//					}.runTaskLater(plugin, 10);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//}
