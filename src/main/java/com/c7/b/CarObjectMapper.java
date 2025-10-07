package com.c7.b;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.List;

public class CarObjectMapper {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        System.out.println(objectMapper.writeValueAsString(car));

        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        car = objectMapper.readValue(json, Car.class);
        System.out.println(objectMapper.writeValueAsString(car));

        json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.get("color"));

        String jsonCarArray =
          "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
        System.out.println(listCar);

        String jsonString = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        car = objectMapper.readValue(jsonString, Car.class);

        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        String year = jsonNodeYear.asText();
        System.out.println(car+" "+year);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
          new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Car.class, new CarSerializer());
        mapper.registerModule(module);
        car = new Car("yellow", "renault");
        System.out.println(mapper.writeValueAsString(car)); // client side looks like: var carJson = {"car_brand":"renault"}

        json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        mapper = new ObjectMapper();
        module =
          new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Car.class, new CarDeserializer());
        mapper.registerModule(module);
        car = mapper.readValue(json, Car.class);
        System.out.println(car);
    }

}
