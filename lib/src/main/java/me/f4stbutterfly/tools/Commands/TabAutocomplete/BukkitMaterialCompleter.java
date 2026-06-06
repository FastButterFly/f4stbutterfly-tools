package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public final class BukkitMaterialCompleter implements SmartCommandArgTabComplete {

	private final List<String> l = new ArrayList<>();

	private static final BukkitMaterialCompleter completer = new BukkitMaterialCompleter();

	public static final BukkitMaterialCompleter getInstance() {
		return completer;
	}

	private BukkitMaterialCompleter() {
		for (Material value : Material.values()) {
			l.add(value.toString().toLowerCase());
		}
	}

	@Override
	public List<String> getComplete() {
		return l;
	}
}
