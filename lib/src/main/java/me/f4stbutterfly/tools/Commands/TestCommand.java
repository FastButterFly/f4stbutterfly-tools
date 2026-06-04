package me.f4stbutterfly.tools.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentType;

public class TestCommand extends SmartCommand {

	private static final Permission COMMAND_USE_PERMISSION = new Permission("f4stbutterfly-tools.testcmd.use");

	public TestCommand(ToolsPlugin plugin) {
		super(
			plugin,
			 "test", 
			 true, 
			 true, 
			 COMMAND_USE_PERMISSION, 
			 new Permission[] { COMMAND_USE_PERMISSION },
			 "f4stbutterfly-tools.testcmd.use"
			);
		this.commandArguments.add(new SmartCommandArgument("test", SmartCommandArgumentType.Required, false, 0));
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		sender.sendMessage("coś tam");
		return false;
	}
}
