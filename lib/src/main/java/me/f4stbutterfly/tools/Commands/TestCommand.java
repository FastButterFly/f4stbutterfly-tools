package me.f4stbutterfly.tools.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentPassContext;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentType;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.TestCompleteer;
import me.f4stbutterfly.tools.Parsers.DummyParser;

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
		this.commandArguments.add(new SmartCommandArgument("test", SmartCommandArgumentType.Required, false, 0, new TestCompleteer()));
		this.commandArguments.add(new SmartCommandArgument("lista", SmartCommandArgumentType.Required, true, 1, null));
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		String val = getRequiredArgumentAsType("test", new DummyParser(), arguments).value;
		sender.sendMessage("coś tam: " + val);
		List<SmartCommandArgumentPassContext<String>> l = getArgumentAsList("lista", new DummyParser(), arguments);
		for(SmartCommandArgumentPassContext<String> a : l) {
			sender.sendMessage("element: " + a.value);
		}
		return false;
	}
}
