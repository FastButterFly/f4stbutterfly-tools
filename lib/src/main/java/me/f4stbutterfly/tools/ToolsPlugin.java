package me.f4stbutterfly.tools;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.f4stbutterfly.tools.Commands.BasicCommands.FeedCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.FlyCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.GamemodeCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.GodCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.HealCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.KillCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.SmartCommandTabAutocomplete;
import me.f4stbutterfly.tools.bstats.Metrics;

public class ToolsPlugin extends JavaPlugin {

	public static final Permission GOD_PERMISSION = new Permission("f4stbutterfly-tools.*");
	public final SmartCommand[] commands = new SmartCommand[] { 
		new GamemodeCommand(this), 
		new HealCommand(this), 
		new GodCommand(this), 
		new FlyCommand(this), 
		new FeedCommand(this),
		new KillCommand(this) };

	public static final String VERSION = "1.0";

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
			HTTPUpdater upd = new HTTPUpdater();
			if(upd.isUpdateNeeded()) {
				getLogger().info("There is new version of the plugin available!");
			}
		}

		int pID = 31801;
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, pID);
	}

	@Override
	public void onDisable() {
		//saveConfig();
	}
}
