package me.f4stbutterfly.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
public final class SpawnManager {
	public static final SpawnManager INSTANCE = new SpawnManager();
	public ToolsPlugin plg;
	private SpawnManager() {}

	public Location spawnpoint = new Location(Bukkit.getWorlds().get(0), 0, 60, 0);

	public void setSpawn(Location loc) {
		this.spawnpoint = loc;
		ConfigManager.spawnpoint.saveAsLocatioon(plg, loc);
	}

	public Location getSpawnlocation() {
		return this.spawnpoint;
	}
}
