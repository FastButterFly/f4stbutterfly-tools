package me.f4stbutterfly.tools.Parsers;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public final class BukkitEnchantmentParser implements IParser<Enchantment> {
	@Override
	public Enchantment parse(String from) throws Exception {
		Enchantment ench = Enchantment.getByKey(NamespacedKey.minecraft(from));
		if(ench != null) {
			return ench;
		} else {
			throw new Exception();
		}
	}
}
