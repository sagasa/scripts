//package com.kilosiberia.expressions;
//
//import org.bukkit.entity.Player;
//
//import com.kilosiberia.SkDeathCollect;
//import com.kilosiberia.util.SkPing;
//
//import ch.njol.skript.expressions.base.SimplePropertyExpression;
//
//public class ExprPing extends SimplePropertyExpression<Player, Integer>{

//    private static String packageName = Bukkit.getServer().getClass().getPackage().getName();
//    private static String version = packageName.substring(packageName.lastIndexOf(".") + 1);
//
//	@Override
//	public Class<? extends Integer> getReturnType() {
//		return Integer.class;
//	}
//
//	@Override
//	protected String getPropertyName() {
//		return "getPing";
//	}
//
//	@Override
//	public Integer convert(Player p) {
//		return SkPing.getPing(p);
//	}

//	private Number getPing(Player p) {
//
//		Class<?> CP;
//		try {
//			CP = Class.forName("org.bukkit.craftbukkit." + version + "entity.CraftPlayer");
//
//			Object CraftPlayer = CP.cast(p);
//
//			Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
//	        Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);
//
//	        Field ping = EntityPlayer.getClass().getDeclaredField("ping");
//
//	        CraftPlayer pl = (org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer) p;
//
//	        int c = pl.getHandle().ping;
//
//	        return ping.getInt(EntityPlayer);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("クラス取得エラー(E1)");
//			return 0;
//		}
//	}
//}
