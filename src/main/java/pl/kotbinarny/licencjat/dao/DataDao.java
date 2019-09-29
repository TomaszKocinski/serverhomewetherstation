package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Sensor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface DataDao extends JpaRepository<Data,Long> {
    List<Data> findBySensor(Sensor sensor);
    List<Data> findByDateAfterOrderByDateAsc(LocalDateTime after);
    List<Data> findBySensorAndDateGreaterThanAndDateLessThanEqual(Sensor sensor, LocalDateTime from, LocalDateTime to);
}
