package me.f4stbutterfly.tools;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public final class HTTPUpdater {
	public boolean isUpdateNeeded() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest r = HttpRequest.newBuilder()
				.uri(new URI(""))
				.GET()
				.build();
		} catch (Exception e) {
			return false;
		}
	}
}
