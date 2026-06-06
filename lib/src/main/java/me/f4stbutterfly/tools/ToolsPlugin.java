package me.f4stbutterfly.tools;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.f4stbutterfly.bstats.Metrics;
import me.f4stbutterfly.tools.Commands.BasicCommands.ClearCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.EnchantCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.EnderchestCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.FeedCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.FlyCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.GamemodeCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.GiveCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.GodCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.HealCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.HelpopCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.InvseeCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.KillCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.KitCommand;
import me.f4stbutterfly.tools.Commands.BasicCommands.RepairCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.SmartCommandTabAutocomplete;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.ToolsKitCompleter;
import me.f4stbutterfly.tools.Events.BukkitEventListener;
import me.f4stbutterfly.tools.Kits.KitManager;

public class ToolsPlugin extends JavaPlugin implements IPermission {
	public final KitManager kitManager = new KitManager(this);
	public static final Permission GOD_PERMISSION = new Permission("f4stbutterfly-tools.*");
	public final SmartCommand[] commands = new SmartCommand[] { 
		new GamemodeCommand(this), 
		new HealCommand(this), 
		new GodCommand(this), 
		new FlyCommand(this), 
		new FeedCommand(this),
		new KillCommand(this),
		new EnderchestCommand(this),
		new InvseeCommand(this),
		new HelpopCommand(this),
		new GiveCommand(this),
		new EnchantCommand(this),
		new RepairCommand(this),
		new ClearCommand(this),
		new KitCommand(this) };

	public static final String VERSION = "1.1";

	@Override
	public boolean hasPermission(CommandSender sender, Permission perm) {
		return sender.isOp() || sender.hasPermission(ToolsPlugin.GOD_PERMISSION) || sender.hasPermission(perm);
	}

	@Override
	public void onEnable() {

		if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			getLogger().log(Level.SEVERE, "PlaceholderAPI is required to run this plugin!");
			Bukkit.getPluginManager().disablePlugin(this);
		}

		BukkitEventListener.instance.plugin = this;
		Bukkit.getPluginManager().registerEvents(BukkitEventListener.instance, this);

		int pID = 31801;
		@SuppressWarnings("unused")
		Metrics m = new Metrics(this, pID);		

		saveDefaultConfig();
		reloadConfig();

		HTTPUpdater upd = new HTTPUpdater();
		if(upd.isUpdateNeeded()) {
			getLogger().info("There is new version of the plugin available!");
		}

		Bukkit.getPluginManager().addPermission(GOD_PERMISSION);

		kitManager.loadKits();
		ToolsKitCompleter.instance.build(this);

		for (int i = 0; i < commands.length; i++) {
			commands[i].getRegisteredPermissions().forEach((e) -> {
				Bukkit.getPluginManager().addPermission(e);
			});

			getCommand(commands[i].getCommandName()).setExecutor(commands[i]);
			getCommand(commands[i].getCommandName()).setTabCompleter(new SmartCommandTabAutocomplete(commands[i]));

		}
	}

	@Override
	public void onDisable() {
		//saveConfig();
	}
}
