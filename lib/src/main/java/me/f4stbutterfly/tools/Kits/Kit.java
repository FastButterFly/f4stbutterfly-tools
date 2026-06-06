package me.f4stbutterfly.tools.Kits;

import java.util.List;

import org.bukkit.permissions.Permission;

public final class Kit {
	public final String kitName;
	public final int cooldownInTicks;
	public final List<KitItemEntry> items;
	public final Permission kitPermission;

	public Kit(String name, int cooldownInSec, List<KitItemEntry> itms) {
		this.kitName = name;
		this.cooldownInTicks = cooldownInSec * 20;
		this.items = itms;
		this.kitPermission = new Permission("f4stbutterfly-tools.kit." + this.kitName);
	}
}
