package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.Statistics;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsDao extends JpaRepository<Statistics,Long> {
    List<Statistics> findBySensor(Sensor sensor);
    Statistics findBySensorAndDateFromAndDateToAndType(Sensor sensor, LocalDateTime from, LocalDateTime to, String type);
}
