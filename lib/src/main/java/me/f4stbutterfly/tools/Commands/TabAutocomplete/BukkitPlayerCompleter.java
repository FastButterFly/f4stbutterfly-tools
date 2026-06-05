package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public final class BukkitPlayerCompleter implements SmartCommandArgTabComplete {
	@Override
	public List<String> getComplete() {
		List<String> names = new ArrayList<>();

		Bukkit.getOnlinePlayers().forEach((plr) -> {
			names.add(plr.getName());
		});
		
		return names;
	}
}
