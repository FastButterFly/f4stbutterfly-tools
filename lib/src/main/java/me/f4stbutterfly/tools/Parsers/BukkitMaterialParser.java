package me.f4stbutterfly.tools.Parsers;

import org.bukkit.Material;

public final class BukkitMaterialParser implements IParser<Material> {
	@Override
	public Material parse(String from) throws Exception {
		return Material.valueOf(from.toUpperCase());
	}
}
