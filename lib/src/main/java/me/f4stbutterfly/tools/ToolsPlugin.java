package me.f4stbutterfly.tools;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.f4stbutterfly.tools.Commands.TestCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.SmartCommandTabAutocomplete;
import me.f4stbutterfly.tools.bstats.Metrics;

public class ToolsPlugin extends JavaPlugin {

	public static final Permission GOD_PERMISSION = new Permission("f4stbutterfly-tools.*");
	public final SmartCommand[] commands = new SmartCommand[] { new TestCommand(this) };

	@Override
	public void onEnable() {
		saveDefaultConfig();
		reloadConfig();
		Bukkit.getPluginManager().addPermission(GOD_PERMISSION);
		for (int i = 0; i < commands.length; i++) {
			commands[i].getRegisteredPermissions().forEach((e) -> {
				Bukkit.getPluginManager().addPermission(e);
			});

			getCommand(commands[i].getCommandName()).setExecutor(commands[i]);
			getCommand(commands[i].getCommandName()).setTabCompleter(new SmartCommandTabAutocomplete(commands[i]));
		}

		int pID = 31801;
		Metrics metrics = new Metrics(this, pID);
	}

	@Override
	public void onDisable() {
		//saveConfig();
	}
}
