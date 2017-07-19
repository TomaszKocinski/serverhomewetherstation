package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.Sensor;

public interface SensorDao extends JpaRepository<Sensor,Integer> {
    Sensor findByName(String name);
}
