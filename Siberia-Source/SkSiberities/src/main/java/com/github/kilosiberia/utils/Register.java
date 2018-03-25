package com.github.kilosiberia.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.BukkitEvent.SiberitiesPacketEvent;
import com.github.kilosiberia.effects.EffAddPlayerTeam;
import com.github.kilosiberia.effects.EffCreateTeam;
import com.github.kilosiberia.effects.EffDeleteTeam;
import com.github.kilosiberia.effects.EffGenAliases;
import com.github.kilosiberia.effects.EffRemovePlayerTeam;
import com.github.kilosiberia.effects.EffSendPacketHash;
import com.github.kilosiberia.effects.EffSendPacketMods;
import com.github.kilosiberia.effects.EffSendPacketPing;
import com.github.kilosiberia.effects.EffSetItemAmount;
import com.github.kilosiberia.effects.EffTeamColor;
import com.github.kilosiberia.effects.SkConfigSave;
import com.github.kilosiberia.effects.SkConfigSaveDefault;
import com.github.kilosiberia.effects.SkConfigSet;
import com.github.kilosiberia.expressions.ExprAmountTeam;
import com.github.kilosiberia.expressions.ExprChannelOfSPacket;
import com.github.kilosiberia.expressions.ExprItemAmount;
import com.github.kilosiberia.expressions.ExprListTeams;
import com.github.kilosiberia.expressions.ExprMemberofTeam;
import com.github.kilosiberia.expressions.ExprMessageOfSPacket;
import com.github.kilosiberia.expressions.ExprModList;
import com.github.kilosiberia.expressions.ExprPlayerHash;
import com.github.kilosiberia.expressions.ExprPlayerOfSPacket;
import com.github.kilosiberia.expressions.ExprSPacketTitle;
import com.github.kilosiberia.expressions.ExprSPacketValue;
import com.github.kilosiberia.expressions.ExprServerHash;
import com.github.kilosiberia.expressions.PlayerPing;
import com.github.kilosiberia.expressions.SkConfigLoad;
import com.github.kilosiberia.expressions.StringSize;
import com.github.kilosiberia.types.SPacket;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.SerializableGetter;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import siberities.rayfall.EffDeleteEffect;
import siberities.rayfall.EffEffectLibAnimatedBallEffect;
import siberities.rayfall.EffEffectLibAtom;
import siberities.rayfall.EffEffectLibBleed;
import siberities.rayfall.EffEffectLibCircle;
import siberities.rayfall.EffEffectLibLine;
import siberities.rayfall.EffEffectLibText;
import siberities.rayfall.EffEffectLibTornado;
import siberities.rayfall.EffEffectLibWave;

public class Register {

	public static void register() {

		Skript.registerEvent("Siberities packet event", SimpleEvent.class, SiberitiesPacketEvent.class,
				"siberities packet");

		EventValues.registerEventValue(SiberitiesPacketEvent.class, SPacket.class,
				new SerializableGetter<SPacket, SiberitiesPacketEvent>() {
					@Override
					public SPacket get(SiberitiesPacketEvent e) {
						return e.getPacket();
					}
				}, 0);

		EventValues.registerEventValue(SiberitiesPacketEvent.class, Player.class,
				new SerializableGetter<Player, SiberitiesPacketEvent>() {

					@Override
					public Player get(SiberitiesPacketEvent e) {
						return e.getPacket().getPlayer();
					}

				}, 0);

		Skript.registerEffect(EffSendPacketPing.class, "send ping packet to %player%");
		Skript.registerEffect(SkConfigSet.class, "set config [path] [of] %string% value [of] %string%");
		Skript.registerEffect(SkConfigSaveDefault.class, "default config (generate|save)");
		Skript.registerEffect(SkConfigSave.class, "config save");
		Skript.registerEffect(EffSendPacketHash.class, "send hash packet to %player%");
		Skript.registerEffect(EffSendPacketMods.class, "send modlist packet to %player%");
		Skript.registerEffect(EffCreateTeam.class, "create team [named] %string%");
		Skript.registerEffect(EffDeleteTeam.class, "delete team [named] %string%");
		Skript.registerEffect(EffAddPlayerTeam.class, "add %player% to team %string%");
		Skript.registerEffect(EffRemovePlayerTeam.class, "remove %player% from team %string%");
		Skript.registerEffect(EffTeamColor.class, "set %string% [team] color to %string%");
		Skript.registerEffect(EffSetItemAmount.class, "set (amount|number|size) item %itemstack% to %number%");

		Skript.registerExpression(StringSize.class, Number.class, ExpressionType.SIMPLE, "size of %string%");
		Skript.registerExpression(SkConfigLoad.class, String.class, ExpressionType.SIMPLE, "get config [of] %string%");
		Skript.registerExpression(PlayerPing.class, Integer.class, ExpressionType.SIMPLE, "ping of last packet");
		Skript.registerExpression(ExprPlayerHash.class, String.class, ExpressionType.SIMPLE, "hash of last packet");
		Skript.registerExpression(ExprServerHash.class, String.class, ExpressionType.SIMPLE, "server's pack hash");
		Skript.registerExpression(ExprModList.class, String.class, ExpressionType.SIMPLE, "mod list of last packet");
		Skript.registerExpression(ExprChannelOfSPacket.class, String.class, ExpressionType.SIMPLE,
				"channel of %spacket%");
		Skript.registerExpression(ExprMessageOfSPacket.class, String.class, ExpressionType.SIMPLE,
				"message of %spacket%");
		Skript.registerExpression(ExprPlayerOfSPacket.class, Player.class, ExpressionType.SIMPLE,
				"player of %spacket%");
		Skript.registerExpression(ExprSPacketTitle.class, String.class, ExpressionType.SIMPLE, "title of %spacket%");
		Skript.registerExpression(ExprSPacketValue.class, String.class, ExpressionType.SIMPLE, "value of %spacket%");
		Skript.registerExpression(ExprListTeams.class, Team.class, ExpressionType.SIMPLE, "list of teams");
		Skript.registerExpression(ExprAmountTeam.class, Number.class, ExpressionType.SIMPLE, "number of team %string%");
		Skript.registerExpression(ExprMemberofTeam.class, OfflinePlayer.class, ExpressionType.SIMPLE, "team member of %string%");
		Skript.registerExpression(ExprItemAmount.class, Number.class, ExpressionType.SIMPLE,
				"(amount|size|number) of item %itemstack%");

		Skript.registerEffect(EffDeleteEffect.class, "(delete|stop) (effect|formation) %string%");
		Skript.registerEffect(EffEffectLibAnimatedBallEffect.class,
				"(spawn|create|apply) (a|the|an) animated ball (effect|formation) (at|on|for|to) %entity/location% with id %string% [with particle %EffectLibParticle%] [offset by %number%, %number%(,| and) %number%]");
		Skript.registerEffect(EffEffectLibAtom.class,
				"(spawn|create|apply) (a|the|an) atom (effect|formation) (at|on|for|to) %entity/location% with id %string%");
		Skript.registerEffect(EffEffectLibBleed.class,
				"(spawn|create|apply) (a|the|an) bleed (effect|formation) (at|on|for|to) %entity/location% with id %string%");
		Skript.registerEffect(EffEffectLibCircle.class,
				"(spawn|create|apply) (a|the|an) circle (effect|formation) (at|on|for) %entity/location% with id %string% [with particle[s] %EffectLibParticle%] [(and|with) radius %number%]");
		Skript.registerEffect(EffEffectLibLine.class,
				"(spawn|create|apply) (a|the|an) line (effect|formation) from %entity/location% to %entity/location% with id %string% [with particle[s] %EffectLibParticle%]");
		Skript.registerEffect(EffEffectLibText.class,
				"(spawn|create|apply) (a|the|an) text (effect|formation) with text %string% as %EffectLibParticle% (at|on|for|to) %entity/location% with id %string%[(,| and) %number% large]");
		Skript.registerEffect(EffEffectLibTornado.class,
				"(spawn|create|apply) (a|the|an) tornado (effect|formation) at %entity/location% with id %string% [with tornado particle[s] %EffectLibParticle% and cloud particle[s] %EffectLibParticle%] [(set|and) radius %number%] [(set|and) max height %number%]");
		Skript.registerEffect(EffEffectLibWave.class,
				"(spawn|create|apply) (a|the|an) wave (effect|formation) at %entity/location% with id %string% [with particle[s] %EffectLibParticle%]");

		Skript.registerEffect(EffGenAliases.class, "generate mod aliases");
	}
}
