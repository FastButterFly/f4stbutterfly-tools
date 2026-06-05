package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class HealCommand extends SmartCommandSimpleCmd {
	private final ToolsPlugin plugin;

	public HealCommand(ToolsPlugin plg) {
		super(plg, "heal");
		this.plugin = plg;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return ConfigManager.command_heal_change.getAsSendableMessage(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()) });
	}
}
