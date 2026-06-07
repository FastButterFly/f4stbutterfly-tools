package me.f4stbutterfly.tools.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.f4stbutterfly.tools.ConfigManager;
import me.f4stbutterfly.tools.ConfigStringReplacement;
import me.f4stbutterfly.tools.SpawnManager;
import me.f4stbutterfly.tools.ToolsPlugin;

public final class BukkitEventListener implements Listener {
	public static final BukkitEventListener instance = new BukkitEventListener();
	public ToolsPlugin plugin;
	private BukkitEventListener() {}

	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent event) {
		event.setJoinMessage(ConfigManager.player_join.getAsSendableWithPlaceholders(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", event.getPlayer().getName()) }, event.getPlayer()));
	}

	@EventHandler
	public void playerQuitEvent(PlayerQuitEvent event) {
		event.setQuitMessage(ConfigManager.player_left.getAsSendableWithPlaceholders(plugin, new ConfigStringReplacement[] { new ConfigStringReplacement("%target%", event.getPlayer().getName()) }, event.getPlayer()));
	}

	@EventHandler
	public void playerRespawnEvent(PlayerRespawnEvent event) {
		if(ConfigManager.settings_respawn_at_spawn.getAsBoolean(plugin) && event.getPlayer().getBedSpawnLocation() == null) {
			event.setRespawnLocation(SpawnManager.INSTANCE.getSpawnlocation());
		}
	}
}
