package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentType;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.ToolsKitCompleter;
import me.f4stbutterfly.tools.Kits.Exception.PlayerNoKitPermissionException;
import me.f4stbutterfly.tools.Kits.Exception.PlayerOnKitCooldownException;
import me.f4stbutterfly.tools.Parsers.DummyParser;

public final class KitCommand extends SmartCommand {
	public final ToolsPlugin plugin;

	private static final Permission USE_PERMISSION = new Permission("f4stbutterfly-tools.kit.use");

	public KitCommand(ToolsPlugin plg) {
		super(plg,
			"kit",
			true, 
			true, 
			USE_PERMISSION, 
			new Permission[] { USE_PERMISSION }, 
			"f4stbutterfly-tools.kit.use");
		
		this.plugin = plg;
		this.commandArguments.add(new SmartCommandArgument("kit", SmartCommandArgumentType.Required, false, 0, ToolsKitCompleter.instance));
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		try {
			this.plugin.kitManager.giveKitToPlayer((Player)sender, getRequiredArgumentAsType("kit", new DummyParser(), arguments).value);
			sender.sendMessage(ConfigManager.command_kit_get.getAsSendableMessage(this.plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%kit%", getRequiredArgumentAsType("kit", new DummyParser(), arguments).value) } ));
		} catch (Exception e) {
			if(e instanceof PlayerOnKitCooldownException) {
				sender.sendMessage(ConfigManager.command_kit_cooldown.getAsSendableMessage(this.plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%kit%", getRequiredArgumentAsType("kit", new DummyParser(), arguments).value) } ));
			} else if(e instanceof PlayerNoKitPermissionException) {
				sender.sendMessage(getNoPermissionMessage("f4stbutterfly-tools.kits." + getRequiredArgumentAsType("kit", new DummyParser(), arguments).value));
			} else {
				sender.sendMessage(ConfigManager.invalid_kit.getAsSendableMessage(plugin, null));
			}
		}

		return false;
	}
}
