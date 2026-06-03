package me.f4stbutterfly.tools;

import org.bukkit.ChatColor;

public enum ConfigManager {
	no_permission("message.no_permission"),
	player_only("message.command_only_for_players"),
	prefix("message.prefix");

	private final String entry;

	ConfigManager(String a) {
		this.entry = a;
	}

	public String getAsString(ToolsPlugin plugin) {
		try {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(entry));
		} catch (NullPointerException e) {
			return "Invalid string (Auto-error)";
		}
	}

	public String getAsSendableMessage(ToolsPlugin plugin) {
		try {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(entry).replace("%prefix%", ConfigManager.prefix.getAsString(plugin)));
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
