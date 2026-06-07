package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.SpawnManager;
import me.f4stbutterfly.tools.TeleportationAPI;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;

public final class SpawnCommmand extends SmartCommand {
	private final ToolsPlugin plugin;
	private static final Permission USE_PERMISSION = new Permission("f4stbutterfly-tools.spawn.use");

	public SpawnCommmand(ToolsPlugin plg) {
		super(plg, 
			"spawn", 
			true, 
			true, 
			USE_PERMISSION, 
			new Permission[] { USE_PERMISSION }, 
			"f4stbutterfly-tools.spawn.use");
		this.plugin = plg;
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		if(TeleportationAPI.INSTANCE.isPlayerRequestAny((Player)sender)) {
			sender.sendMessage(ConfigManager.teleportation_already_req.getAsSendableMessage(plugin, null));
			return false;
		}
		TeleportationAPI.INSTANCE.sendPlayerTeleportationRequestToLocation((Player)sender, SpawnManager.INSTANCE.getSpawnlocation(), plugin);
		sender.sendMessage(ConfigManager.teleportation_await_tp.getAsSendableMessage(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%t%", ConfigManager.settings_tp_time.getAsString(plugin, null)) } ));
		return false;
	}
}
