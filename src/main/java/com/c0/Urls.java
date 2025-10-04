package com.c0;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.IDN;
import org.apache.commons.validator.routines.UrlValidator;
import java.net.http.*;

public final class Urls {

  public static void main(String[] args) {
    String urlString = "https://example.com/path?query=1";
    System.out.println(isValidHttpUrl("http://example.com"));
    System.out.println(isValidHttpUrl("https://example.com/path?query=1"));
    System.out.println(isValidHttpUrl("ftp://example.com")); // false
    System.out.println(isValidHttpUrl("http://")); // false
    System.out.println(isValidHttpUrl("http://exa mple.com")); // false
    System.out.println(isValidHttpUrl(null)); // false
    System.out.println(isValidHttpUrl("   ")); // false

    UrlValidator v = new UrlValidator(new String[] {"http","https"});
    boolean ok = v.isValid(urlString);
  }

  public static boolean isReachable(String url) {
    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest req = HttpRequest.newBuilder(URI.create(url)).method("HEAD", HttpRequest.BodyPublishers.noBody()).build();
      int code = client.send(req, HttpResponse.BodyHandlers.discarding()).statusCode();
      return code >= 200 && code < 400;
    } catch (Exception e) { return false; }
  }

  public static boolean isValidHttpUrl(String s) {
    if (s == null || s.isBlank()) return false;
    final String input = s.trim();
    try {
      URI uri = new URI(input);
      String scheme = uri.getScheme();
      if (scheme == null) return false;
      if (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https")) return false;

      // Require an authority/host (rejects relative URLs)
      String host = uri.getHost();
      if (host == null || IDN.toASCII(host).isEmpty()) return false;

      int port = uri.getPort();
      if (port != -1 && (port < 1 || port > 65535)) return false;

      // Optional: ensure path/query are valid but don’t require them
      return true;
    } catch (URISyntaxException e) {
      return false;
    }
  }
}

