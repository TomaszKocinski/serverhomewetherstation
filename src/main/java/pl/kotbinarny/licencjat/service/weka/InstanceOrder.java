package pl.kotbinarny.licencjat.service.weka;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum InstanceOrder {
    TEMPERATURE, PRESSURE, HUMIDITY, LIGHT, DATE;

    static String getCommaSeperetedValuesBesideDate() {
        return Arrays.stream(InstanceOrder.values())
                .filter(instanceOrder -> instanceOrder != DATE)
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    static String getDate(){
        return DATE.name().toLowerCase();
    }
}
