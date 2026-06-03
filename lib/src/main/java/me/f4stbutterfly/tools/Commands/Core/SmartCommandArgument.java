package me.f4stbutterfly.tools.Commands.Core;

public record SmartCommandArgument(
	String argName,
	SmartCommandArgumentType type,
	boolean isList,
	int argIndex
) {}
