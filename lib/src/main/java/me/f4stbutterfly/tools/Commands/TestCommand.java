package me.f4stbutterfly.tools.Commands;

import org.bukkit.command.CommandSender;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;

public class TestCommand extends SmartCommand {
	public TestCommand(ToolsPlugin plugin) {
		super(plugin, "test", true, false, null, null);
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		sender.sendMessage("coś tam");
		return false;
	}
}
