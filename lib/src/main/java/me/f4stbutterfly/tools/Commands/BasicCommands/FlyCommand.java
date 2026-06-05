package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class FlyCommand extends SmartCommandSimpleCmd {
	private final ToolsPlugin plg;

	public FlyCommand(ToolsPlugin plg) {
		super(plg, "fly");
		this.plg = plg;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		if(!target.isFlying()) {
			target.setAllowFlight(true);
			target.setFlying(true);
		} else {
			target.setFlying(false);
			target.setAllowFlight(false);
		}
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return ConfigManager.command_fly_change.getAsSendableMessage(plg, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()), new ConfigStringReplacement("%mode%", Boolean.toString(target.isFlying()))});
	}
}
