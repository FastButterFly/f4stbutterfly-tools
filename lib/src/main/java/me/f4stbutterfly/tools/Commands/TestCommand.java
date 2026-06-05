package me.f4stbutterfly.tools.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;

public final class TestCommand extends SmartCommandSimpleCmd {

	public TestCommand(ToolsPlugin plg) {
		super(plg, "test");
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return "Target's ping: " + target.getPing();
	}
}
