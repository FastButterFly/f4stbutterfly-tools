package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.List;

public abstract interface SmartCommandArgTabComplete {
	abstract List<String> getComplete();
}
