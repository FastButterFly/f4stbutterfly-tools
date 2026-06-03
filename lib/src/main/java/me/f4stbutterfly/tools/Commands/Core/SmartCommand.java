package me.f4stbutterfly.tools.Commands.Core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;

public abstract class SmartCommand extends SmartCommandArgsContext implements CommandExecutor {

	private final String commandName;
	private final boolean playerRequired;
	private final boolean permissionRequired;
	private final Permission usePermission;
	private final ToolsPlugin plugin;
	private final List<Permission> permissions = new ArrayList<>();

	private final boolean hasPermission(CommandSender sender, Permission perm) {
		return sender.isOp() || sender.hasPermission(ToolsPlugin.GOD_PERMISSION) || sender.hasPermission(perm);
	}

	public final List<Permission> getRegisteredPermissions() {
		return permissions;
	}

	public final String getCommandName() {
		return commandName;
	}

	abstract void whenExecuted(CommandSender sender, String[] arguments);
	public SmartCommand(ToolsPlugin ToolsPlugin, String commandN, boolean isPlayerRequired, boolean isPermissionRequired, Permission commandPermission, Permission[] perms) {
		this.playerRequired = isPermissionRequired;
		this.permissionRequired = isPermissionRequired;
		this.usePermission = commandPermission;
		this.plugin = ToolsPlugin;
		this.commandName = commandN;
		if(perms != null) {
			for (int i=0; i < perms.length; i++) {
				permissions.add(perms[i]);
			}
		}
	}

	@Override
	public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
