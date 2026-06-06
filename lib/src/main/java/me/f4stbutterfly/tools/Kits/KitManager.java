package me.f4stbutterfly.tools.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Parsers.BukkitEnchantmentParser;

public final class KitManager {
	private final KitConfigFile configFile;

	public final List<Kit> kits = new ArrayList<>();
	private final ToolsPlugin plugin;

	public KitManager(ToolsPlugin plg) {
		this.configFile = new KitConfigFile(plg);
		this.plugin = plg;
	}

	public void loadKits() {
		kits.clear();
		for (String key : configFile.config.getConfigurationSection("kits").getKeys(false)) {
			try {
				loadKit(key);
			} catch (Exception e) {
				this.plugin.getLogger().info("An error occurred while loading kit: " + key);
				e.printStackTrace();
			}
		}
	}

	public void loadKit(String kitName) throws Exception {
		this.plugin.getLogger().info("Loading kit: " + kitName);
		ConfigurationSection section = configFile.config.getConfigurationSection("kits").getConfigurationSection(kitName);

		int cooldown = section.getInt("cooldown");

		ConfigurationSection itemSection = section.getConfigurationSection("items");
		List<KitItemEntry> itms = new ArrayList<>();

		for (String key : itemSection.getKeys(false)) {
			ConfigurationSection currentItemSection = itemSection.getConfigurationSection(key);

			Material itemMat = Material.valueOf(key.toUpperCase());
			int amount = currentItemSection.getInt("ammount");
			String dname = currentItemSection.getString("dname");

			List<KitItemEnchEntry> enchs = new ArrayList<>();

			ConfigurationSection enchantmentSection = currentItemSection.getConfigurationSection("enchantments");
			if(enchantmentSection != null) {
				for (String ekey : enchantmentSection.getKeys(false)) {
					enchs.add(new KitItemEnchEntry(new BukkitEnchantmentParser().parse(ekey), enchantmentSection.getInt(key)));
				}
			}

			itms.add(new KitItemEntry(itemMat, dname, amount, enchs));
		}
		kits.add(new Kit(kitName, cooldown, itms));
	}
}
