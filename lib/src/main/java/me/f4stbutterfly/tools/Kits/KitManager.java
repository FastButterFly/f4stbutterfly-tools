package me.f4stbutterfly.tools.Kits;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.scheduler.BukkitRunnable;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Kits.Exception.PlayerOnKitCooldownException;
import me.f4stbutterfly.tools.Parsers.BukkitEnchantmentParser;

public final class KitManager {

	private static final Permission BYPASS_PERMISSION = new Permission("f4stbutterfly-tools.bypass_kit_cooldown");

	private record kEntry(UUID usedBy, Kit kit, BukkitRunnable task) {}

	private class iTask extends BukkitRunnable {
		public kEntry entry;
		@Override
		public void run() {
			entr.remove(entry);
		}
	}

	private final KitConfigFile configFile;

	public final List<Kit> kits = new ArrayList<>();
	private final List<kEntry> entr = new ArrayList<>();
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

		kits.forEach((kit) -> {
			Bukkit.getPluginManager().addPermission(kit.kitPermission);
		});
	}

	public Kit getKitByName(String kit) throws Exception {
		for (Kit array : (Kit[])kits.toArray()) {
			if(array.kitName == kit) {
				return array;
			}
		}

		throw new Exception();
	}

	public void giveKitToPlayer(Player player, String kitName) throws Exception {
		Kit kit = getKitByName(kitName);
		for (kEntry e : (kEntry[])entr.toArray()) {
			if(e.usedBy() == player.getUniqueId() && e.kit() == kit) throw new PlayerOnKitCooldownException();
		}

		kit.getItems().forEach((item) -> {
			player.getInventory().addItem(item);
		});

		if(!plugin.hasPermission(player, BYPASS_PERMISSION)) {
			iTask task = new iTask();

			kEntry entry = new kEntry(player.getUniqueId(), kit, task);

			task.entry = entry;

			entr.add(entry);
			task.runTaskLater(plugin, kit.cooldownInTicks);
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
