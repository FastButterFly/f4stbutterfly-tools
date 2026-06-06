package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class ClearCommand extends SmartCommandSimpleCmd {
	private final ToolsPlugin plugin;

	public ClearCommand(ToolsPlugin p) {
		super(p, "clear");
		this.plugin = p;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		target.getInventory().clear();
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return ConfigManager.command_clear_cleared.getAsSendableMessage(plugin, null);
	}
}
