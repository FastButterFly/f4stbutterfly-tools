package me.f4stbutterfly.tools.Commands.BasicCommands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ToolsPlugin;
import me.f4stbutterfly.tools.Commands.Core.SmartCommand;

public final class RepairCommand extends SmartCommand {
	private final ToolsPlugin plugin;

	private static final Permission USE_PERMISSION = new Permission("f4stbutterfly-tools.repair.use");

	public RepairCommand(ToolsPlugin plg) {
		super(plg, "repair", true, true, USE_PERMISSION, new Permission[] { USE_PERMISSION }, "f4stbutterfly-tools.repair.use");
		this.plugin = plg;
	}

	@Override
	public boolean whenExecuted(CommandSender sender, String[] arguments) {
		Player p = (Player)sender;
		ItemStack stack = p.getInventory().getItemInMainHand();
		if(stack != null && stack.getType() != Material.AIR) {
			ItemMeta meta = stack.getItemMeta();
			if(meta instanceof Damageable damageable) {
				damageable.setDamage(0);

				stack.setItemMeta(meta);
			}
		}

		p.sendMessage(ConfigManager.command_repair_rep.getAsSendableMessage(plugin, null));

		return false;
	}
}
