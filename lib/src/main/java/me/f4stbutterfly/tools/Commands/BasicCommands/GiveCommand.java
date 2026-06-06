package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgument;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentPassContext;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandArgumentType;
import me.f4stbutterfly.tools.Commands.Core.SmartCommandSimpleCmd;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.BukkitMaterialCompleter;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.BukkitPlayerCompleter;
import me.f4stbutterfly.tools.Commands.TabAutocomplete.IntegerCompleter;
import me.f4stbutterfly.tools.Parsers.BukkitMaterialParser;
import me.f4stbutterfly.tools.Parsers.DummyParser;

public class GiveCommand extends SmartCommandSimpleCmd {

	private final ToolsPlugin plg;

	public GiveCommand(ToolsPlugin p) {
		super(p, "give");
		this.plg = p;
		this.playerRequired = true;
		this.commandArguments.clear();

		this.commandArguments.add(new SmartCommandArgument("target", SmartCommandArgumentType.Required, false, 0, new BukkitPlayerCompleter()));
		this.commandArguments.add(new SmartCommandArgument("item", SmartCommandArgumentType.Required, false, 1, BukkitMaterialCompleter.getInstance()));
		this.commandArguments.add(new SmartCommandArgument("amount", SmartCommandArgumentType.Optional, false, 2, new IntegerCompleter()));
	}

	private Integer getAmmountToGive(String[] args) {
		SmartCommandArgumentPassContext<String> raw = getRequiredArgumentAsType("amount", new DummyParser(), args);
		if(raw.isValid()) {
			try {
				return Integer.valueOf(raw.value);
			} catch (Exception e) {
				return null;
			}
		} else {
			return 1;
		}
	}

	private Material getMaterialToGive(String[] args) {
		SmartCommandArgumentPassContext<Material> raw = getRequiredArgumentAsType("item", new BukkitMaterialParser(), args);
		if(raw.isValid) {
			return raw.value;
		} else {
			return null;
		}
	}

	@Override
	protected void perTarget(CommandSender sender, Player target, String[] args) {
		Material mat = getMaterialToGive(args);
		Integer amount = getAmmountToGive(args);

		if(amount == null) {
			sender.sendMessage(ConfigManager.invalid_nan.getAsSendableMessage(plg, null));
			return;
		}

		if(mat == null) {
			sender.sendMessage(ConfigManager.invalid_material.getAsSendableMessage(plg, null));
			return;
		}

		ItemStack stack = new ItemStack(mat, amount);

		target.getInventory().addItem(stack);
	}

	@Override
	protected String perTargetMessage(CommandSender sender, Player target, String[] args) {
		if(getAmmountToGive(args) == null || getMaterialToGive(args) == null) return null;
		return ConfigManager.command_give_gave.getAsSendableMessage(plg, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", target.getName()), new ConfigStringReplacement("%amount%", getAmmountToGive(args).toString()), new ConfigStringReplacement("%item%", getMaterialToGive(args).toString()) });
	}
}
