package me.f4stbutterfly.tools.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		this.kitPermission = new Permission("f4stbutterfly-tools.kits." + this.kitName);
	}

	public final List<ItemStack> getItems() {
		List<ItemStack> l = new ArrayList<>();
		items.forEach((e) -> {
			ItemStack item = new ItemStack(e.itemMat(), e.amount());
			if(e.dname() != null) {
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', e.dname()));
				item.setItemMeta(meta);
			}

			e.ench().forEach((ench) -> {
				item.addUnsafeEnchantment(ench.ench(), ench.level());
			});

			l.add(item);
		});
		return l;
	}
}
