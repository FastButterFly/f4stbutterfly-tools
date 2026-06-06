package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

public final class IntegerCompleter implements SmartCommandArgTabComplete {
	@Override
	public List<String> getComplete() {
		List<String> l = new ArrayList<>();
		l.add("1");
		l.add("10");
		l.add("100");
		return l;
	}
}
