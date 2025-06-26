package seko.testServer;

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
public class ApplicationSekoLabelServer {

  private static final String VALID_ACCESS_KEY = "67E35A45A35108BD201120805697AE55BDE3CBCE85AD327AEF";

  public static void main(String[] args) {
    SpringApplication.run(ApplicationSekoLabelServer.class, args);
  }

  @RestController
  @RequestMapping("/api")
  public static class TestController {


    @PostMapping("/labels/printshipment")
    public ResponseEntity<String> handlePost(
      @RequestHeader(value = "Access-Key", required = false) String accessKey) throws IOException {
      if (!VALID_ACCESS_KEY.equals(accessKey)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body("{\"error\": \"Invalid or missing Access-Key\"}");
      }
      String responseJson = "";
      responseJson = "seko/fromSeko_for_LABEL_PNG_100X150.json";
//      responseJson = "seko/fromSeko_for_LABEL_PDF_100X150.json";
      ClassPathResource resource = new ClassPathResource(responseJson);
      String json = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
      return ResponseEntity.ok()
        .header("Content-Type", "application/json")
        .body(json);
    }
  }
}
