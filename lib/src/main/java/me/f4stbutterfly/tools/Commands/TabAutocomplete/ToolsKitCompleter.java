package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

import me.f4stbutterfly.tools.ToolsPlugin;

public final class ToolsKitCompleter implements SmartCommandArgTabComplete {

	private final List<String> l = new ArrayList<>();

	public static final ToolsKitCompleter instance = new ToolsKitCompleter();

	public void build(ToolsPlugin plg) {
		l.clear();
		plg.kitManager.kits.forEach((kit) -> {
			l.add(kit.kitName);
		});
	}

	@Override
	public List<String> getComplete() {
		return l;
	}
}
