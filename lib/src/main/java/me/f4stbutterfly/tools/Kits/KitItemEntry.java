package me.f4stbutterfly.tools.Kits;

import java.util.List;

import org.bukkit.Material;

public record KitItemEntry(Material itemMat, String dname, int amount, List<KitItemEnchEntry> ench) {}
