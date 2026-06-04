package me.f4stbutterfly.tools.Commands.Core;

import me.f4stbutterfly.tools.Commands.TabAutocomplete.SmartCommandArgTabComplete;

public record SmartCommandArgument(
	String argName,
	SmartCommandArgumentType type,
	boolean isList,
	int argIndex,
	SmartCommandArgTabComplete complete
) {}
