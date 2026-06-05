package me.f4stbutterfly.tools;

import org.bukkit.ChatColor;

public enum ConfigManager {
	no_permission("message.no_permission"),
	player_only("message.command_only_for_players"),
	prefix("message.prefix"),
	proper_usage("message.proper_usage"),
	invalid_player("message.invalid_player"),
	invalid_gamemode("message.invalid_gamemode"),
	command_gamemode_change("message.commands.gamemode"),
	command_god_change("message.commands.god"),
	command_heal_change("message.commands.heal"),
	command_feed_fed("message.commands.feed"),
	command_fly_change("message.commands.fly"),
	command_kill_killed("message.commands.kill");

	private final String entry;

	ConfigManager(String a) {
		this.entry = a;
	}

	public String getAsString(ToolsPlugin plugin, ConfigStringReplacement[] str_to_replace) {
		try {
			String text = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(entry));

			if(str_to_replace != null) {
				for(int i=0; i < str_to_replace.length; i++) {
					text = text.replace(str_to_replace[i].what(), str_to_replace[i].to());
				}
			}
			
			return text;
		} catch (NullPointerException e) {
			return "Invalid string (Auto-error)";
		}
	}

	public String getAsSendableMessage(ToolsPlugin plugin, ConfigStringReplacement[] str_to_replace) {
		try {
			return getAsString(plugin, str_to_replace).replace("%prefix%", ConfigManager.prefix.getAsString(plugin, str_to_replace));
		} catch (NullPointerException e) {
			return "Invalid string (Auto-error)";
		}
	}

	public int getAsInt(ToolsPlugin plugin) {
		try {
			return plugin.getConfig().getInt(entry);
		} catch (NullPointerException e) {
			return -9900002;
		}
	}

	public double getAsDouble(ToolsPlugin plugin) {
		try {
			return plugin.getConfig().getDouble(entry);
		} catch (NullPointerException e) {
			return -9900002;
		}
	}

	public boolean getAsBoolean(ToolsPlugin plugin) {
		try {
			return plugin.getConfig().getBoolean(entry);
		} catch (NullPointerException e) {
			return false;
		}
	}
}
