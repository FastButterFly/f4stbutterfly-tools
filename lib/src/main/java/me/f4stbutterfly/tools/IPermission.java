package me.f4stbutterfly.tools;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface IPermission {
	public boolean hasPermission(CommandSender sender, Permission perm);
}
