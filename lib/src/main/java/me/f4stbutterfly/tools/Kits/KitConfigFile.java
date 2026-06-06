package me.f4stbutterfly.tools.Kits;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.f4stbutterfly.tools.ToolsPlugin;

public final class KitConfigFile {
	private File file;
	public FileConfiguration config;

	private void a(ToolsPlugin plg) {
		file = new File(plg.getDataFolder(), "kits.yml");
		if(!file.exists()) {
			plg.saveResource("kits.yml", false);
			a(plg);
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	public KitConfigFile(ToolsPlugin plg) {
		a(plg);
	}
}
