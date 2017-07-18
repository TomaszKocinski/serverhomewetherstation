package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.SensorData;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface SensorDataDao extends JpaRepository<SensorData,Integer> {
}
