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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface TemperatureDataService {
    List<TemperatureData> findAll();
    void addData(BigDecimal value, String NameOfSensor);
    List<TemperatureBySensorDTO> findAllSortedBySensor();
    TemperatureBySensorFromToDTO findAllSortedBySensorHighAndLowerDate(LocalDateTime from, LocalDateTime to);
    List<DateValueDTO> findAllBySensorHighAndLowerDate(Sensor sensor, LocalDateTime from, LocalDateTime to);
    <T> T findAllSortedBySensorHighAndLowerDateUniqu(Supplier<T> creator,
                                                            BiFunction<Sensor, T, List<TemperatureData>> biFunction,
                                                            BiConsumer<List<DateValueDTO>, String> biConsumer);
}
