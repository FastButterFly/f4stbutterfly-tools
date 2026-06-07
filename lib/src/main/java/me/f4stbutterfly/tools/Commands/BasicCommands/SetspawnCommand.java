package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.SpawnManager;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;

public final class SetspawnCommand extends SmartCommand {
	private final ToolsPlugin plugin;
	private static final Permission USE_PERMISSION = new Permission("f4stbutterfly-tools.setspawn.use");

	public SetspawnCommand(ToolsPlugin plg) {
		super(plg, 
			"setspawn", 
			true, 
			true, 
			USE_PERMISSION, 
			new Permission[] { USE_PERMISSION }, 
			"f4stbutterfly-tools.setspawn.use");
		this.plugin = plg;
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		Player p = (Player)sender;
		SpawnManager.INSTANCE.setSpawn(p.getLocation());
		p.sendMessage(ConfigManager.command_setspawn_set.getAsSendableMessage(plugin, null));
		return false;
	}
}
