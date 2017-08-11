package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.Statistics;

import java.util.List;

public interface StatisticsDao extends JpaRepository<Statistics,Long> {
    List<Statistics> findBySensor(Sensor sensor);
}
