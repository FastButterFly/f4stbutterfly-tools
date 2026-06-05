package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class FeedCommand extends SmartCommandSimpleCmd {
	private final ToolsPlugin plugin;

	public FeedCommand(ToolsPlugin plg) {
		super(plg, "feed");
		this.plugin = plg;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		target.setFoodLevel(20);
		target.setSaturation(500);
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return ConfigManager.command_feed_fed.getAsSendableMessage(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()) });
	}
}
