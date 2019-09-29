package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.dto.DateValueDTO;
import pl.kotbinarny.licencjat.dto.DataAndPredictionBySensorDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorFromToDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface TemperatureDataService {
    List<Data> findAll();
    Data addData(BigDecimal value, BigDecimal hum, BigDecimal pressure, BigDecimal light, String name);
    List<DataAndPredictionBySensorDTO> findAllSortedBySensor();
    TemperatureBySensorFromToDTO findAllSortedBySensorHighAndLowerDate(LocalDateTime from, LocalDateTime to);
    List<DateValueDTO> findAllBySensorHighAndLowerDate(Sensor sensor, LocalDateTime from, LocalDateTime to);
}
