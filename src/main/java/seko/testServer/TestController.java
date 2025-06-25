package seko.testServer;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
curl -X POST http://localhost:8080/api/labels/printshipment -H "Access-Key: 67E35A45A35108BD201120805697AE55AD327AEF"
      .uri("https://test.omniparcelreturns.com/api/labels/printshipment")
      .uri("http://localhost:8080/api/labels/printshipment")
      .header("Access-Key", "67E35A45A35108BD201120805697AE55BDE3CBCE85AD327AEF")
*/

@RestController
@RequestMapping("/api")
public class TestController {

  private static final String VALID_ACCESS_KEY = "67E35A45A35108BD201120805697AE55BDE3CBCE85AD327AEF";

  @PostMapping("/labels/printshipment")
  public ResponseEntity<String> handlePost(
    @RequestHeader(value = "Access-Key", required = false) String accessKey) throws IOException {
    if (!VALID_ACCESS_KEY.equals(accessKey)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body("{\"error\": \"Invalid or missing Access-Key\"}");
    }
    String responseJson = "";
    responseJson = "seko/fromSeko_for_LABEL_PNG_100X150.json";
//    responseJson = "seko/fromSeko_for_LABEL_PDF_100X150.json";
    ClassPathResource resource = new ClassPathResource(responseJson);
    String json = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    return ResponseEntity.ok()
      .header("Content-Type", "application/json")
      .body(json);
  }

}