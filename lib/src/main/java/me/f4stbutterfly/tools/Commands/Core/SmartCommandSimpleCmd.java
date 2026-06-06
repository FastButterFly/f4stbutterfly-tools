package me.f4stbutterfly.tools.Commands.Core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.PlayerSelector;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.BukkitPlayerCompleter;
import me.f4stbutterfly.tools.Parsers.DummyParser;

public abstract class SmartCommandSimpleCmd extends SmartCommand {

	private final ToolsPlugin plg;

	public SmartCommandSimpleCmd(ToolsPlugin plg, String name) {
		super(plg,
			name,
			false,
			true,
			new Permission("f4stbutterfly-tools._.use".replace("_", name)),
			new Permission[] { new Permission("f4stbutterfly-tools._.use".replace("_", name)) },
			"f4stbutterfly-tools._.use".replace("_", name));
		
		this.commandArguments.add(new SmartCommandArgument("target", SmartCommandArgumentType.Optional, false, 0, new BukkitPlayerCompleter()));
		this.plg = plg;
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		SmartCommandArgumentPassContext<String> playerSelector = getRequiredArgumentAsType("target", new DummyParser(), arguments);
		List<Player> targets = new ArrayList<>();
		if(!playerSelector.isValid) {
			if(!(sender instanceof Player)) {sender.sendMessage("This command without target argument can only by run by player!"); return false; }
			targets.add((Player)sender);
		} else {
			targets = PlayerSelector.playersFromString(playerSelector.value);
		}

		targets.forEach((target) -> {
			if(target != null) {
				perTarget(sender, target, arguments);
				if(perTargetMessage(sender, target, arguments) != null) {
					sender.sendMessage(perTargetMessage(sender, target, arguments));
				}
			} else {
				sender.sendMessage(ConfigManager.invalid_player.getAsSendableMessage(plg, null));
			}
		});

		return false;
	}

	abstract protected void perTarget(CommandSender sender, Player target, String[] args);
	abstract protected String perTargetMessage(CommandSender sender, Player target, String[] args);
}
