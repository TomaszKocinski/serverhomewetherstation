package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.dto.DateValueDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorFromToDTO;
import pl.kotbinarny.licencjat.dto.TemperatureSensorFromToDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface TemperatureDataService {
    List<TemperatureData> findAll();
    void addData(BigDecimal value, String NameOfSensor);
    List<TemperatureBySensorDTO> findAllSortedBySensor();
    TemperatureBySensorFromToDTO findAllSortedBySensorHighAndLowerDate(LocalDateTime from, LocalDateTime to);
    List<DateValueDTO> findAllBySensorHighAndLowerDate(Sensor sensor, LocalDateTime from, LocalDateTime to);
}
