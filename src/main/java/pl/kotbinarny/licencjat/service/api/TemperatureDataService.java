package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface TemperatureDataService {
    List<TemperatureData> findAll();
    void addData(BigDecimal value, String NameOfSensor);
    List<TemperatureBySensorDTO> findAllSortedBySensor();
}
