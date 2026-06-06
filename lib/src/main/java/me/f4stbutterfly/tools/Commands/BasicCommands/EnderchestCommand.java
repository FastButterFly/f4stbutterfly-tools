package me.f4stbutterfly.tools.Commands.BasicCommands;

//import java.util.ArrayList;
//import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

//import me.f4stbutterfly.tools.PlayerSelector;
import me.f4stbutterfly.tools.ToolsPlugin;
//import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentPassContext;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;
//import me.f4stbutterfly.tools.Parsers.DummyParser;

public final class EnderchestCommand extends SmartCommandSimpleCmd {
	//private final Permission USE_OTHERS_PERMISSION = new Permission("f4stbutterfly-tools.enderchest.others");

	public EnderchestCommand(ToolsPlugin plugin) {
		super(plugin, "enderchest");
		this.playerRequired = true;
		//this.permissions.add(USE_OTHERS_PERMISSION);
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		/* SmartCommandArgumentPassContext<String> playerSelector = getRequiredArgumentAsType("target", new DummyParser(), args);
		List<Player> targets = new ArrayList<>();

		if(!playerSelector.isValid) {
			targets.add((Player)sender);
		} else {
			targets = PlayerSelector.playersFromString(playerSelector.value);
		}

		Player plr = (Player)sender;

		if(targets.size() > 1 || targets.get(0) != plr) {
			if(this.hasPermission(sender, USE_OTHERS_PERMISSION)) {
				plr.openInventory(target.getEnderChest());
			}
		} else {
			plr.openInventory(target.getEnderChest());
		} */

		Player plr = (Player)sender;
		plr.openInventory(target.getInventory());
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		return null;
	}
}
