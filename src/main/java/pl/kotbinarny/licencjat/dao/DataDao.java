package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Sensor;

import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface DataDao extends JpaRepository<Data,Integer> {
    List<Data> findAllBySensor(Long sensor);
}
