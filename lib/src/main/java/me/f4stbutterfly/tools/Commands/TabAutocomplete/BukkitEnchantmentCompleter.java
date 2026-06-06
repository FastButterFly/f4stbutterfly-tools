package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;

public final class BukkitEnchantmentCompleter implements SmartCommandArgTabComplete {

	private final List<String> l = new ArrayList<>();
	
	private static final BukkitEnchantmentCompleter completer = new BukkitEnchantmentCompleter();

	public static final BukkitEnchantmentCompleter getInstance() {
		return completer;
	}

	private BukkitEnchantmentCompleter() {
		for (Enchantment value : Enchantment.values()) {
			l.add(value.getKey().getKey());
		}
	}

	@Override
	public List<String> getComplete() {
		return l;
	}
}
