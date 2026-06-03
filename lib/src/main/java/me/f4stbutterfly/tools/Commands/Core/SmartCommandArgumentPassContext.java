package me.f4stbutterfly.tools.Commands.Core;

public class SmartCommandArgumentPassContext<T> {
	public T value;

	public boolean isValid = false;
	public String Reason = "";

	public boolean isValid() {
		return isValid;
	}

	public boolean isFailReason_ArgNotProvided() {
		return Reason == "ARG_NOT_FOUND";
	}

	public boolean isFailReason_ParseFail() {
		return Reason == "ARG_PARSE_FAILED";
	}
}
