package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.Data;

import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface SensorDataService {
    List<Data> findAll();

    void addData(Integer value,String NameOfSensor);
}
