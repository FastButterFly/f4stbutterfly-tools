package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentPassContext;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentType;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.BukkitGamemodeCompleter;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.BukkitPlayerCompleter;
import me.f4stbutterfly.tools.Parsers.BukkitGamemodeParser;

public final class GamemodeCommand extends SmartCommandSimpleCmd {

	private final ToolsPlugin plg;

	public GamemodeCommand(ToolsPlugin plg) {
		super(plg, "gamemode");
		this.plg = plg;
		this.commandArguments.clear();
		this.commandArguments.add(new SmartCommandArgument("gamemode", SmartCommandArgumentType.Required, false, 0, BukkitGamemodeCompleter.getInstance()));
		this.commandArguments.add(new SmartCommandArgument("target", SmartCommandArgumentType.Optional, false, 1, new BukkitPlayerCompleter()));
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		SmartCommandArgumentPassContext<GameMode> gm = getRequiredArgumentAsType("gamemode", new BukkitGamemodeParser(), args);
		if(!gm.isValid && gm.isFailReason_ParseFail()) {
			sender.sendMessage(ConfigManager.invalid_gamemode.getAsSendableMessage(this.plg, null));
			return;
		}

		GameMode targetGameMode = gm.value;

		target.setGameMode(targetGameMode);
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		SmartCommandArgumentPassContext<GameMode> gm = getRequiredArgumentAsType("gamemode", new BukkitGamemodeParser(), args);
		if(!gm.isValid && gm.isFailReason_ParseFail()) {
			return ConfigManager.invalid_gamemode.getAsSendableMessage(this.plg, null);
		}

		GameMode targetGameMode = gm.value;

		return ConfigManager.command_gamemode_change.getAsSendableMessage(plg, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()), new ConfigStringReplacement("%gamemode%", targetGameMode.toString())});
	}
}
