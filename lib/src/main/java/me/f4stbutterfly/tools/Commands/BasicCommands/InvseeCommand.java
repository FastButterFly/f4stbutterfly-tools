package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class InvseeCommand extends SmartCommandSimpleCmd {
	public InvseeCommand(ToolsPlugin plg) {
		super(plg, "invsee");
		this.playerRequired = true;
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return null;
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		Player p = (Player)sender;
		p.openInventory(target.getInventory());
	}
}
