package me.f4stbutterfly.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class PlayerSelector {
	public static final List<Player> playersFromString(String selector) {
		List<Player> a = new ArrayList<>();
		if(selector.equalsIgnoreCase("*")) {
			Bukkit.getOnlinePlayers().forEach((plr) -> {
				a.add(plr);
			});
		} else {
			String[] b = selector.split(",");
			for(int i=0;i<b.length;i++) {
				a.add(Bukkit.getPlayer(b[i]));
			}
		}

		return a;
	}
}
