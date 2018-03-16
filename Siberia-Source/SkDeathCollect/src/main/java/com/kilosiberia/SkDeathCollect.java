package com.kilosiberia;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.kilosiberia.expressions.ExprExpandAttacker;
import com.kilosiberia.expressions.ExprExpandDamageCause;
//import com.kilosiberia.expressions.ExprPing;
//import com.kilosiberia.util.PingSetterTimer;
//import com.kilosiberia.util.SkPing;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class SkDeathCollect extends JavaPlugin {

//	public final SkPing Ping = new SkPing(this);
//	public final PingSetterTimer pst = new PingSetterTimer(this);

	public void onEnable(){
		getLogger().info("Enabled SkDeathCollect");
		Skript.registerAddon(this);
		Skript.registerExpression
			(ExprExpandAttacker.class, Player.class, ExpressionType.SIMPLE, "exp[and[ ]]attacker");
		Skript.registerExpression
			(ExprExpandDamageCause.class, String.class, ExpressionType.SIMPLE, "exp[and[ ]]damagecause");
//		Skript.registerExpression
//			(ExprPing.class, Integer.class, ExpressionType.PROPERTY, "%player%[[']s] get[ ]ping");
//		new PingSetterTimer(this).runTaskTimer(this, 0, 1200);
	}
}

