package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;

public final class SmartCommandTabAutocomplete implements TabCompleter {

	private final SmartCommand command;

	public SmartCommandTabAutocomplete(SmartCommand c) {
		this.command = c;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		int index = args.length - 1;
		for (SmartCommandArgument arg : this.command.commandArguments) {
			if (arg.argIndex() == index && arg.complete() != null) {
				String input = args[index].toLowerCase();
				return arg.complete().getComplete().stream()
					.filter(s -> s.toLowerCase().startsWith(input))
					.toList();
			}
		}
		return Collections.emptyList();
	}
}
