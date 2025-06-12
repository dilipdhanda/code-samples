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
 */

@RestController
@RequestMapping("/api")
public class TestController {

  private static final String VALID_ACCESS_KEY = "67E35A45A35108BD201120805697AE55AD327AEF";

  @PostMapping("/labels/printshipment")
  public ResponseEntity<String> handlePost(
    @RequestHeader(value = "Access-Key", required = false) String accessKey) throws IOException {

    if (!VALID_ACCESS_KEY.equals(accessKey)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body("{\"error\": \"Invalid or missing Access-Key\"}");
    }

    // Read response.json from classpath
    ClassPathResource resource = new ClassPathResource("sekoResponseLABEL_PDF_100X175.json");
    String json = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    return ResponseEntity.ok()
      .header("Content-Type", "application/json")
      .body(json);
  }

  String getSmallerTestJsonResponse(){
    return "{\n" +
      "\t\"CarrierId\": 1295,\n" +
      "\t\"CarrierName\": \"Canada Post Returns\",\n" +
      "\t\"IsFreightForward\": true,\n" +
      "\t\"IsOvernight\": false,\n" +
      "\t\"IsSaturdayDelivery\": false,\n" +
      "\t\"IsRural\": false,\n" +
      "\t\"HasTrackPaks\": false,\n" +
      "\t\"Message\": \"Connote created and print queued.\",\n" +
      "\t\"Errors\": [],\n" +
      "\t\"SiteId\": 1507352,\n" +
      "\t\"Consignments\": [\n" +
      "\t\t{\n" +
      "\t\t\t\"Connote\": \"7321717122606827\",\n" +
      "\t\t\t\"TrackingUrl\": \"http://track.omniparcel.com/1507352-7321717122606827\",\n" +
      "\t\t\t\"CarrierTrackingUrl\": null,\n" +
      "\t\t\t\"Cost\": 17.96,\n" +
      "\t\t\t\"CarrierType\": 75,\n" +
      "\t\t\t\"IsSaturdayDelivery\": false,\n" +
      "\t\t\t\"IsRural\": false,\n" +
      "\t\t\t\"IsOvernight\": false,\n" +
      "\t\t\t\"HasTrackPaks\": false,\n" +
      "\t\t\t\"ConsignmentId\": 108751448,\n" +
      "\t\t\t\"OutputFiles\": {\n" +
      "\t\t\t\t\"LABEL_PDF\": [\n" +
      "\t\t\t\t\t\"JVBERi0xLjQKJdP0zOEKMSAwIG9iago8PAovQ3JlYXRpb25EYXRlKEQ6MjAyNTA1MjgwNTA2MzYrMTAnMDAnKQovVGl0bGUoT21uaVBhcmNlbCBGcmVpZ2h0IExhYmVsKQovQXV0ayMTU0OTYKJSVFT0YK\"\n" +
      "\t\t\t\t]\n" +
      "\t\t\t},\n" +
      "\t\t\t\"Items\": [\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"PartNo\": 1,\n" +
      "\t\t\t\t\t\"TrackingNo\": \"7321717122606827\",\n" +
      "\t\t\t\t\t\"Barcode\": \"7321717122606827\",\n" +
      "\t\t\t\t\t\"InternalBarcode\": \"46110010875144839001\",\n" +
      "\t\t\t\t\t\"Charge\": 17.96,\n" +
      "\t\t\t\t\t\"Charge_FAF\": 0,\n" +
      "\t\t\t\t\t\"Charge_Rural\": 0,\n" +
      "\t\t\t\t\t\"Charge_SatDel\": 0,\n" +
      "\t\t\t\t\t\"Charge_Insurance\": 0,\n" +
      "\t\t\t\t\t\"IsTrackPack\": false,\n" +
      "\t\t\t\t\t\"BarcodeText\": \"7321717122606827\",\n" +
      "\t\t\t\t\t\"TrackingBarcode\": \"7321717122606827\",\n" +
      "\t\t\t\t\t\"TrackingBarcode2\": \"7321717122606827\"\n" +
      "\t\t\t\t}\n" +
      "\t\t\t]\n" +
      "\t\t}\n" +
      "\t],\n" +
      "\t\"DestinationPort\": \"YYZ\",\n" +
      "\t\"Downloads\": [],\n" +
      "\t\"CarrierType\": 75,\n" +
      "\t\"AlertPath\": null,\n" +
      "\t\"Notifications\": [],\n" +
      "\t\"InvoiceResponse\": \"No Invoice found\",\n" +
      "\t\"LogoPath\": \"http://cdn.omniparcel.com/images/canadapost_logo.png\"\n" +
      "}"; // Mocked JSON for demonstration
  }
}