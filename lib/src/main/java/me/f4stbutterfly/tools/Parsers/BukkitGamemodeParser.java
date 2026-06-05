package me.f4stbutterfly.tools.Parsers;

import org.bukkit.GameMode;

public final class BukkitGamemodeParser implements IParser<GameMode> {
	@Override
	public GameMode parse(String from) throws Exception {
		switch (from) {
			case "0":
			case "s":
			case "survial":
				return GameMode.SURVIVAL;
			case "1":
			case "c":
			case "creative":
				return GameMode.CREATIVE;
			case "2":
			case "a":
			case "adventure":
				return GameMode.ADVENTURE;
			case "3":
			case "sp":
			case "spectator":
				return GameMode.SPECTATOR;
			default:
				throw new IllegalArgumentException();
		}
	}
}
