package me.f4stbutterfly.tools.Commands.BasicCommands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentPassContext;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentType;
import me.f4stbutterfly.tools.Parsers.DummyParser;

public final class HelpopCommand extends SmartCommand {
	private final ToolsPlugin plg;

	private static final Permission RECIVE_PERMISSION = new Permission("f4stbutterfly-tools.helpop.recive");

	public HelpopCommand(ToolsPlugin plugin) {
		super(plugin, 
			"helpop", 
			true, 
			false, 
			null, 
			new Permission[] { RECIVE_PERMISSION }, 
			"f4stbutterfly-tools.helpop.recive");
		
		this.plg = plugin;
		this.commandArguments.add(new SmartCommandArgument("message", SmartCommandArgumentType.Required, true, 0, null));
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		List<SmartCommandArgumentPassContext<String>> rawMsg = getArgumentAsList("message", new DummyParser(), arguments);
		StringBuilder msg = new StringBuilder();
		for (int i=0;i<rawMsg.size();i++) {
			if((i == rawMsg.size() - 1)) {
				msg.append(rawMsg.get(i).value);
			} else {
				msg.append(rawMsg.get(i).value + " ");
			}
		}

		sender.sendMessage(ConfigManager.command_helpop_sent.getAsSendableMessage(plg, null));

		Bukkit.getOnlinePlayers().forEach((plr) -> {
			if(hasPermission(plr, RECIVE_PERMISSION)) {
				plr.sendMessage(ConfigManager.command_helpop_recived.getAsSendableMessage(plg, new ConfigStringReplacement[] { new ConfigStringReplacement("%sender%", sender.getName()), new ConfigStringReplacement("%message%", msg.toString()) }));
			}
		});

		return false;
	}
}
