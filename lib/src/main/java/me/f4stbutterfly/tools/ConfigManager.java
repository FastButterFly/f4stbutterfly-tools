package me.f4stbutterfly.tools;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public enum ConfigManager {
	no_permission("message.no_permission", ConfigFile.Message),
	player_only("message.command_only_for_players", ConfigFile.Message),
	prefix("message.prefix", ConfigFile.Message),
	proper_usage("message.proper_usage", ConfigFile.Message),
	invalid_player("message.invalid_player", ConfigFile.Message),
	invalid_gamemode("message.invalid_gamemode", ConfigFile.Message),
	command_gamemode_change("message.commands.gamemode", ConfigFile.Message),
	command_god_change("message.commands.god", ConfigFile.Message),
	command_heal_change("message.commands.heal", ConfigFile.Message),
	command_feed_fed("message.commands.feed", ConfigFile.Message),
	command_fly_change("message.commands.fly", ConfigFile.Message),
	command_kill_killed("message.commands.kill", ConfigFile.Message),
	command_helpop_sent("message.commands.helpop.sent", ConfigFile.Message),
	command_helpop_recived("message.commands.helpop.recived", ConfigFile.Message),
	invalid_material("message.invalid_material", ConfigFile.Message),
	command_give_gave("message.commands.give", ConfigFile.Message),
	invalid_nan("message.invalid_nan", ConfigFile.Message),
	invalid_enchantment("message.invalid_enchantment", ConfigFile.Message),
	command_enchant_enchanted("message.commands.enchant", ConfigFile.Message),
	command_repair_rep("message.commands.repaired", ConfigFile.Message),
	command_clear_cleared("message.commands.clear", ConfigFile.Message),
	player_join("message.player_joined", ConfigFile.Message),
	player_left("message.player_quit", ConfigFile.Message),
	invalid_kit("message.invalid_kit", ConfigFile.Message),
	command_kit_get("message.commands.kit.recived", ConfigFile.Message),
	command_kit_cooldown("message.commands.kit.on_cooldown", ConfigFile.Message),
	settings_respawn_at_spawn("respawn-players-without-bed-at-spawn", ConfigFile.Config),
	command_setspawn_set("message.commands.spawn.setspawn", ConfigFile.Message),
	spawnpoint("spawn_info", ConfigFile.Config),
	settings_tp_time("teleport_time", ConfigFile.Config),
	teleportation_already_req("message.teleportation_already_req", ConfigFile.Message),
	teleportation_await_tp("message.teleportation_await_tp", ConfigFile.Message),
	teleportation_move("message.teleportation_move", ConfigFile.Message);

	private enum ConfigFile {
		Message("message.yml"),
		Config(null);

		private FileConfiguration config;
		private String path;
		public File f = null;
		private ConfigFile(String path) {
			this.path = path;
		}

		public FileConfiguration requestConfigFile(ToolsPlugin plugin) {
			if(path == null) { return plugin.getConfig(); }
			if(f == null) {
				f = new File(plugin.getDataFolder(), path);
				if(!f.exists()) {
					plugin.saveResource(path, false);
					return requestConfigFile(plugin);
				}
				this.config = YamlConfiguration.loadConfiguration(f);
			} else {
				if(config == null) {
					config = YamlConfiguration.loadConfiguration(f);
				}
			}
			return config;
		}

		public void save(ToolsPlugin plugin) {
			if(path == null) { try {
				plugin.getConfig().save(new File(plugin.getDataFolder(), "config.yml"));
			} catch (IOException e) {
				e.printStackTrace();
			} }
			else {
				try {
					config.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private final String entry;
	private final ConfigFile file;

	ConfigManager(String a, ConfigFile f) {
		this.entry = a;
		this.file = f;
	}

	public FileConfiguration getConfig(ToolsPlugin plg) {
		return file.requestConfigFile(plg);
	}

	public String getAsString(ToolsPlugin plugin, ConfigStringReplacement[] str_to_replace) {
		try {
			String text = ChatColor.translateAlternateColorCodes('&', getConfig(plugin).getString(entry));

			if(str_to_replace != null) {
				for(int i=0; i < str_to_replace.length; i++) {
					text = text.replace(str_to_replace[i].what(), str_to_replace[i].to());
				}
			}

			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid string (Auto-error)";
		}
	}

	public String getAsSendableMessage(ToolsPlugin plugin, ConfigStringReplacement[] str_to_replace) {
		try {
			return getAsString(plugin, str_to_replace).replace("%prefix%", ConfigManager.prefix.getAsString(plugin, str_to_replace));
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid string (Auto-error)";
		}
	}

	public String getAsSendableWithPlaceholders(ToolsPlugin plugin, ConfigStringReplacement[] str_to_replace, Player plr) {
		String str = getAsSendableMessage(plugin, str_to_replace);
		str = PlaceholderAPI.setBracketPlaceholders(plr, str);
		return str;
	}

	public int getAsInt(ToolsPlugin plugin) {
		try {
			return getConfig(plugin).getInt(entry);
		} catch (Exception e) {
			return -9900002;
		}
	}

	public double getAsDouble(ToolsPlugin plugin) {
		try {
			return getConfig(plugin).getDouble(entry);
		} catch (Exception e) {
			return -9900002;
		}
	}

	public boolean getAsBoolean(ToolsPlugin plugin) {
		try {
			return getConfig(plugin).getBoolean(entry);
		} catch (Exception e) {
			return false;
		}
	}

	public Location getAsLocation(ToolsPlugin plugin) {
		try {
			Double X = getConfig(plugin).getDouble(entry + ".x");
			Double Y = getConfig(plugin).getDouble(entry + ".y");
			Double Z = getConfig(plugin).getDouble(entry + ".z");
			return new Location(Bukkit.getWorlds().get(0), X, Y, Z);
		} catch (Exception e) {
			e.printStackTrace();
			return new Location(Bukkit.getWorlds().get(0), Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		}
	}

	public void saveAsLocatioon(ToolsPlugin plugin, Location loc) {
		try {
			getConfig(plugin).set(entry + ".x", loc.getX());
			getConfig(plugin).set(entry + ".y", loc.getY());
			getConfig(plugin).set(entry + ".z", loc.getZ());
			file.save(plugin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
