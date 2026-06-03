package me.f4stbutterfly.tools;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.f4stbutterfly.tools.Commands.Core.SmartCommand;

public class ToolsPlugin extends JavaPlugin {

	public static final Permission GOD_PERMISSION = new Permission("f4stbutterfly-tools.*");
	public final SmartCommand[] commands = new SmartCommand[] {};

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().addPermission(GOD_PERMISSION);
		for (int i = 0; i < commands.length; i++) {
			commands[i].getRegisteredPermissions().forEach((e) -> {
				Bukkit.getPluginManager().addPermission(e);
			});

			getCommand(commands[i].getCommandName()).setExecutor(commands[i]);
		}
	}

	@Override
	public void onDisable() {
	}
}
