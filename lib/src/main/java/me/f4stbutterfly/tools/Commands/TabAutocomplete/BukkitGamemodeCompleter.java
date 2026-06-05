package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

public final class BukkitGamemodeCompleter implements SmartCommandArgTabComplete {

	private static final BukkitGamemodeCompleter instance = new BukkitGamemodeCompleter();
	public final List<String> gamemodes = new ArrayList<>();

	public static final BukkitGamemodeCompleter getInstance() {
		return instance;
	}

	private BukkitGamemodeCompleter() {
		gamemodes.add("0");
		gamemodes.add("s");
		gamemodes.add("survival");
		gamemodes.add("1");
		gamemodes.add("c");
		gamemodes.add("creative");
		gamemodes.add("2");
		gamemodes.add("a");
		gamemodes.add("adventure");
		gamemodes.add("3");
		gamemodes.add("sp");
		gamemodes.add("survival");
	}

	@Override
	public List<String> getComplete() {
		return gamemodes;
	}
}
