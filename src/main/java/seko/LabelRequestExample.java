package seko;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class LabelRequestExample {

    public static void main(String[] args) {
        LabelClient client = new LabelClient();

        Address address = new Address();
        address.setBuildingName("");
        address.setStreetAddress("155 SNOW BLVD");
        address.setSuburb("Concord");
        address.setCity("Ontario");
        address.setPostCode("L4K 4N9");
        address.setCountryCode("CA");

        Party origin = new Party();
        origin.setAddress(address);
        origin.setName("Destination Person");
        origin.setContactPerson("Destination person");
        origin.setPhoneNumber("0411375332");
        origin.setEmail("test@destinationcomp.com");

        Party destination = new Party();
        destination.setAddress(address);
        destination.setId(1);
        destination.setName("RETURNS – retailer name");
        destination.setContactPerson("JOHN SMITH");
        destination.setPhoneNumber("1800 951 411");
        destination.setEmail("destinationemail@test.com");
        destination.setDeliveryInstructions("");
        destination.setCostCentreName("mysite.com");

        Commodity commodity = new Commodity();
        commodity.setDescription("Leave With Me Dress In Navy Fl");
        commodity.setUnits("1");
        commodity.setUnitValue("62.95");
        commodity.setUnitKg("0.1");
        commodity.setCurrency("AUD");
        commodity.setItemNo("1212121");
        commodity.setSKU("ST1306B-white");
        commodity.setReason("Too Big");
        commodity.setCommodityCode("HS456");

        PackageItem packageItem = new PackageItem();
        packageItem.setName("Leave With Me Dress In Navy Fl");
        packageItem.setId("173646");
        packageItem.setHeight("0.1");
        packageItem.setWidth("0.1");
        packageItem.setKg("0.1");
        packageItem.setLength("70");
        packageItem.setType("Box");

        LabelRequest request = new LabelRequest();
        request.setDeliveryReference("OrderNumber123");
        request.setReference2("RMA1234");
        request.setReference3("");
        request.setRPSProcessing("true");
        request.setOrigin(origin);
        request.setDestination(destination);
        request.setCommodities(List.of(commodity));
        request.setPackages(List.of(packageItem));
        request.setIssignaturerequired(false);
        request.setPrintToPrinter(false);
        request.setCarrierLogo(true);
        request.setOutputs(List.of("LABEL_PDF_100X150"));
        request.setSendTrackingEmail(false);
        request.setShipType("INBOUND");
        request.setSendLabel("Y");
        request.setService("");
        request.setCarrier("");

        client.sendLabelRequest(request).doOnNext(System.out::println).block();
    }
}

class LabelClient {
    private final WebClient webClient;

    public LabelClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://test.omniparcelreturns.com")
                .defaultHeader("Access-Key", "67E35A45A35108BD201120805697AE55BDE3CBCE85AD327AEF")
                .defaultHeader("Content-Type", "application/json; charset=utf-8")
                .defaultHeader("Cookie", "test_cookie=1; ApplicationGatewayAffinity=...") // shorten cookie
                .build();
    }

    public Mono<LabelResponse> sendLabelRequest(LabelRequest request) {
        return webClient.post()
                .uri("/api/labels/printshipment")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LabelResponse.class);
    }
}

@Data
class Address {
    private String BuildingName;
    private String StreetAddress;
    private String Suburb;
    private String City;
    private String PostCode;
    private String CountryCode;
}

@Data
class Party {
    private Address Address;
    private Integer Id;
    private String Name;
    private String ContactPerson;
    private String PhoneNumber;
    private String Email;
    private String DeliveryInstructions;
    private String CostCentreName;
}

@Data
class Commodity {
    private String Description;
    private String Units;
    private String UnitValue;
    private String UnitKg;
    private String Country;
    private String Currency;
    private String ItemNo;
    private String SKU;
    private String Reason;
    private String ReturnAction;
    private String CommodityCode;
}

@Data
class PackageItem {
    private String Name;
    private String Id;
    private String Height;
    private String Width;
    private String Kg;
    private String Length;
    private String Type;
}

@Data
class LabelRequest {
    private String DeliveryReference;
    private String Reference2;
    private String Reference3;
    private String RPSProcessing;
    private Party Origin;
    private Party Destination;
    private List<Commodity> Commodities;
    private List<PackageItem> Packages;
    private boolean issignaturerequired;
    private boolean PrintToPrinter;
    private boolean CarrierLogo;
    private List<String> Outputs;
    private boolean SendTrackingEmail;
    private String ShipType;
    private String SendLabel;
    private String Service;
    private String Carrier;
}

@Data
class LabelResponse {
    private int CarrierId;
    private String CarrierName;
    private boolean IsFreightForward;
    private boolean IsOvernight;
    private boolean IsSaturdayDelivery;
    private boolean IsRural;
    private boolean HasTrackPaks;
    private String Message;
    private List<String> Errors;
    private int SiteId;
    private List<Consignment> Consignments;
    private String DestinationPort;
    private List<String> Downloads;
    private int CarrierType;
    private String AlertPath;
    private List<String> Notifications;
    private String InvoiceResponse;
    private String LogoPath;
}

@Data
class Consignment {
    private String Connote;
    private String TrackingUrl;
    private String CarrierTrackingUrl;
    private double Cost;
    private int CarrierType;
    private boolean IsSaturdayDelivery;
    private boolean IsRural;
    private boolean IsOvernight;
    private boolean HasTrackPaks;
    private long ConsignmentId;
    private OutputFiles OutputFiles;
    private List<Item> Items;
}

@Data
class OutputFiles {
    private List<String> LABEL_PDF_100X150;
}

@Data
class Item {
    private int PartNo;
    private String TrackingNo;
    private String Barcode;
    private String InternalBarcode;
    private double Charge;
    private double Charge_FAF;
    private double Charge_Rural;
    private double Charge_SatDel;
    private double Charge_Insurance;
    private boolean IsTrackPack;
    private String BarcodeText;
    private String TrackingBarcode;
    private String TrackingBarcode2;
}
