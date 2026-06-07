package me.f4stbutterfly.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class TeleportationAPI {
	private TeleportationAPI() {}
	public static final TeleportationAPI INSTANCE = new TeleportationAPI();

	private record TeleportQueryEntryToLoc(Player target, Location loc, TeleportTaskLoc task) {}
	private record TeleportQueryEntryToPlr(Player target, Player loc, TeleportTaskPlayer task) {}

	private class TeleportTaskLoc extends BukkitRunnable {
		public TeleportQueryEntryToLoc entry;
		@Override
		public void run() {
			teleportToLocRequests.remove(entry);
			try {
				entry.target.teleport(entry.loc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class TeleportTaskPlayer extends BukkitRunnable {
		public TeleportQueryEntryToPlr entry;
		@Override
		public void run() {
			teleportToPlrRequests.remove(entry);
			try {
				entry.target.teleport(entry.loc.getLocation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private final List<TeleportQueryEntryToLoc> teleportToLocRequests = new ArrayList<>();
	private final List<TeleportQueryEntryToPlr> teleportToPlrRequests = new ArrayList<>();

	private boolean isPlayerRequestToLoc(Player p) {
		for (TeleportQueryEntryToLoc teleportToLocRequest : teleportToLocRequests) {
			if (teleportToLocRequest.target.getUniqueId() == p.getUniqueId()) {
				return true;
			}
		}

		return false;
	}

	private boolean isPlayerRequestToPlr(Player p) {
		for (TeleportQueryEntryToPlr teleportToLocRequest : teleportToPlrRequests) {
			if (teleportToLocRequest.target.getUniqueId() == p.getUniqueId()) {
				return true;
			}
		}

		return false;
	}

	public void cancelLoc(Player p) {
		for(int i = 0; i < teleportToLocRequests.size();i++) {
			TeleportQueryEntryToLoc teleportToLocRequest = teleportToLocRequests.get(i);
			if (teleportToLocRequest.target.getUniqueId() == p.getUniqueId()) {
				teleportToLocRequest.task.cancel();
				teleportToLocRequests.remove(teleportToLocRequest);
				break;
			}	
		}
	}

	public boolean isPlayerRequestAny(Player p) {
		return isPlayerRequestToLoc(p) || isPlayerRequestToPlr(p);
	}

	public void sendPlayerTeleportationRequestToLocation(Player p, Location loc, ToolsPlugin plg) {
		if(isPlayerRequestAny(p)) return;
		TeleportTaskLoc task = new TeleportTaskLoc();
		TeleportQueryEntryToLoc t = new TeleportQueryEntryToLoc(p, loc, task);
		teleportToLocRequests.add(t);
		task.entry = t;
		task.runTaskLater(plg, ConfigManager.settings_tp_time.getAsInt(plg) * 20);
	}
}
