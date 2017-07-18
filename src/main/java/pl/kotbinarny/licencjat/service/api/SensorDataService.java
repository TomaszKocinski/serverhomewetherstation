package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.SensorData;

import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface SensorDataService {
    List<SensorData> findAll();
}
