package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class KillCommand extends SmartCommandSimpleCmd {
	private final ToolsPlugin plugin;

	public KillCommand(ToolsPlugin plg) {
		super(plg, "kill");
		this.plugin = plg;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		target.setHealth(0);
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return ConfigManager.command_kill_killed.getAsSendableMessage(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()) });
	}
}
