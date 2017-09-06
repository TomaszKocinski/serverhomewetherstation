package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.domain.Sensor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface TemperatureDataDao extends JpaRepository<TemperatureData,Long> {
    List<TemperatureData> findBySensor(Sensor sensor);
    List<TemperatureData> findBySensorAndDateGreaterThanAndDateLessThanEqual(Sensor sensor, LocalDateTime from, LocalDateTime to);
}
