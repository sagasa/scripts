package com.github.kilosiberia.utils;

import javax.annotation.Nullable;

import org.bukkit.scoreboard.Team;

import com.github.kilosiberia.types.SPacket;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.YggdrasilSerializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import de.slikey.effectlib.util.ParticleEffect;

public class RegisterElements {

	public RegisterElements() {
	}

	static {
		Classes.registerClass(new ClassInfo<>(SPacket.class, "spacket").name("SPacket").user("spacket?s?")
				.defaultExpression(new EventValueExpression<SPacket>(SPacket.class))
				.serializer(new YggdrasilSerializer<SPacket>()));

		Classes.registerClass(new ClassInfo<>(Team.class, "team").name("Team").user("team?s?")
				.defaultExpression(new EventValueExpression<Team>(Team.class)).parser(new Parser<Team>() {

					@Override
					public Team parse(String s, ParseContext context) {
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(Team o, int flags) {
						return o.getName();
					}

					@Override
					public String toVariableNameString(Team o) {
						return o.getName();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

				}));

		Classes.registerClass(new ClassInfo<ParticleEffect>(ParticleEffect.class, "effectlibparticle")
				.user("effectlibperticle").parser(new Parser<ParticleEffect>() {
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

					@Override
					@Nullable
					public ParticleEffect parse(String value, ParseContext cont) {
						try {
							return ParticleEffect.valueOf(value.replace(" ", "_").trim().toUpperCase());
						} catch (IllegalArgumentException exception) {
							return null;
						}
					}

					@Override
					public String toString(ParticleEffect eff, int in) {
						return eff.getName().replace("_", " ").toLowerCase();
					}

					@Override
					public String toVariableNameString(ParticleEffect eff) {
						return eff.getName().replace("_", " ").toLowerCase();
					}
				}));

	}
}
