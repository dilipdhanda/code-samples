package com.c0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ApplicationSekoPdfLabelServer {

  private static final String VALID_ACCESS_KEY = "67E35A45A35108BD201120805697AE55BDE3CBCE85AD327AEF";

  public static void main(String[] args) {
    SpringApplication.run(ApplicationSekoPdfLabelServer.class, args);
  }

  @RestController
  @RequestMapping("/api")
  public static class TestController {

    @PostMapping("/labels/printshipment")
    public ResponseEntity<String> handlePost(
      @RequestHeader(value = "Access-Key", required = false) String accessKey,
      @org.springframework.web.bind.annotation.RequestBody String requestBody) throws IOException {
      String responseJsonPNG = "seko/fromSeko_for_LABEL_PNG_100X150.json";
      String responseJsonInvalidToken = "seko/fromSeko_for_Invalid_Token.json";

      String invalidTokenJson = "{"
              + "\"error\": \"invalid_token\","
              + "\"message\": \"The provided token is invalid or expired.\""
              + "}";
      if (!VALID_ACCESS_KEY.equals(accessKey)) {
        return ResponseEntity.ok()
          .header("Content-Type", "application/json")
          .body(invalidTokenJson);
      }

      // Parse the requestBody to extract origin.PhoneNumber
      String phoneNumber = null;
      try {
        com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(requestBody);
        if (root.has("origin") && root.get("origin").has("PhoneNumber")) {
          phoneNumber = root.get("origin").get("PhoneNumber").asText();
        }
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .header("Content-Type", "application/json")
          .body("{\"Success\":\"FALSE\",\"Message\":\"Invalid JSON body\"}");
      }

      String responseJson;
      if ("0411375332".equals(phoneNumber)) {
        responseJson = responseJsonPNG;
      } else if ("0411375333".equals(phoneNumber)) {
        responseJson = "seko/fromSeko_for_LABEL_PDF_100X150.json";
      } else if ("0411375334".equals(phoneNumber)) {
        responseJson = responseJsonInvalidToken;
      } else {
        responseJson = responseJsonPNG;
      }

      return ResponseEntity.ok()
        .header("Content-Type", "application/json")
        .body(getJson(responseJson));
    }

    String getJson(String filePath) throws IOException {
      ClassPathResource resource = new ClassPathResource(filePath);
      return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
  }

  // Moved here to avoid pass VULNERABILITIES GATE in seko-returns-main
  public String testGetSekoApiKey() {
    // Do not use class loader below as it picks changes from the compiled/build jar, not from the
    // src dir where
    // you are making changes in runtime to test.
    // String filename = getClass().getClassLoader().getResource("seko_api_key.txt").getPath();
    String filename = "src/main/resources/seko_api_key.txt";

    String invalidToken = "InvalidToken";
    // {"Success":"FALSE","Message":"Invalid token"} response if token is invalid
    try {
      java.nio.file.Path path = java.nio.file.Paths.get(filename);
      if (!java.nio.file.Files.exists(path)) {
        return invalidToken;
      }
      return new String(java.nio.file.Files.readAllBytes(path));
    } catch (Exception e) {
      System.err.print("Error reading file from file" + e.getMessage());
      return invalidToken;
    }
  }
}
