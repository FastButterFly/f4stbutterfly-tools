package me.f4stbutterfly.tools;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class HTTPUpdater {
	public boolean isUpdateNeeded() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest r = HttpRequest.newBuilder()
				.uri(new URI("https://raw.githubusercontent.com/FastButterFly/f4stbutterfly-tools/refs/heads/main/version.txt"))
				.GET()
				.build();
			
			HttpResponse<String> res = client.send(r, HttpResponse.BodyHandlers.ofString());
			return !ToolsPlugin.VERSION.equalsIgnoreCase(res.body());
		} catch (Exception e) {
			return false;
		}
	}
}
