package me.f4stbutterfly.tools.Kits.Exception;

public final class PlayerNoKitPermissionException extends Exception {
	@Override
	public String getMessage() {
		return "Player dosn't have permissions for that";
	}
}
