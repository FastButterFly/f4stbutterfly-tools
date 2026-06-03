package me.f4stbutterfly.tools;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolsPlugin extends JavaPlugin {

	public static final Permission GOD_PERMISSION = new Permission("f4stbutterfly-tools.*");
	private static final Permission[] permissions = new Permission[] {};

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().addPermission(GOD_PERMISSION);
		for (int i = 0; i < permissions.length; i++) {
			Bukkit.getPluginManager().addPermission(permissions[i]);
		}
	}

	@Override
	public void onDisable() {
	}
}
