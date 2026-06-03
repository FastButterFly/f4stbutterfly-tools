package me.f4stbutterfly.tools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public abstract class SmartCommand implements CommandExecutor {

	private final boolean playerRequired;
	private final boolean permissionRequired;
	private final Permission usePermission;
	private final ToolsPlugin plugin;

	private final boolean hasPermission(CommandSender sender, Permission perm) {
		return sender.isOp() || sender.hasPermission(ToolsPlugin.GOD_PERMISSION) || sender.hasPermission(perm);
	}

	abstract void whenExecuted(CommandSender sender, String[] arguments);
	public SmartCommand(ToolsPlugin ToolsPlugin, boolean isPlayerRequired, boolean isPermissionRequired, Permission commandPermission) {
		this.playerRequired = isPermissionRequired;
		this.permissionRequired = isPermissionRequired;
		this.usePermission = commandPermission;
		this.plugin = ToolsPlugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(playerRequired) { 
			Player p = (Player)sender;
			if(p == null) {
				sender.sendMessage(ConfigManager.player_only.getAsSendableMessage(plugin, null));
				return false;
			}
		}

		if(permissionRequired && !hasPermission(sender, usePermission)) {
			sender.sendMessage(ConfigManager.no_permission.getAsSendableMessage(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%permission%", usePermission.toString()) } ));
		}
		return false;
	}
}
