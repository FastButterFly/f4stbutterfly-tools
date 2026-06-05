package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class GodCommand extends SmartCommandSimpleCmd {
	private final ToolsPlugin plg;

	public GodCommand(ToolsPlugin plugin) {
		super(plugin, "god");
		this.plg = plugin;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		target.setInvulnerable(!target.isInvulnerable());
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return ConfigManager.command_god_change.getAsSendableMessage(plg, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()), new ConfigStringReplacement("%mode%", Boolean.toString(target.isInvulnerable()))});
	}
}
