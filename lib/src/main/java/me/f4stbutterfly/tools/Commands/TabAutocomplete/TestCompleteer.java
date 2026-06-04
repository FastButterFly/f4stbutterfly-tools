package me.f4stbutterfly.tools.Commands.TabAutocomplete;

import java.util.ArrayList;
import java.util.List;

public class TestCompleteer implements SmartCommandArgTabComplete {
	@Override
	public List<String> getComplete() {
		List<String> a = new ArrayList<>();
		a.add("test1");
		a.add("test2");
		return a;
	}
}
