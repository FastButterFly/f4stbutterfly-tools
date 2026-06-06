package me.f4stbutterfly.tools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

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
	command_kill_killed("message.commands.kill"),
	command_helpop_sent("message.commands.helpop.sent"),
	command_helpop_recived("message.commands.helpop.recived"),
	invalid_material("message.invalid_material"),
	command_give_gave("message.commands.give"),
	invalid_nan("message.invalid_nan"),
	invalid_enchantment("message.invalid_enchantment"),
	command_enchant_enchanted("message.commands.enchant"),
	command_repair_rep("message.commands.repaired"),
	command_clear_cleared("message.commands.clear"),
	player_join("message.player_joined"),
	player_left("message.player_quit"),
	invalid_kit("message.invalid_kit"),
	command_kit_get("message.commands.kit.recived"),
	command_kit_cooldown("message.commands.kit.on_cooldown");

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
			return plugin.getConfig().getInt(entry);
		} catch (Exception e) {
			return -9900002;
		}
	}

	public double getAsDouble(ToolsPlugin plugin) {
		try {
			return plugin.getConfig().getDouble(entry);
		} catch (Exception e) {
			return -9900002;
		}
	}

	public boolean getAsBoolean(ToolsPlugin plugin) {
		try {
			return plugin.getConfig().getBoolean(entry);
		} catch (Exception e) {
			return false;
		}
	}
}
