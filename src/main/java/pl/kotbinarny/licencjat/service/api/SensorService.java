package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.Sensor;

import java.util.List;

public interface SensorService{
    List<Sensor> findAll();
    Sensor findByName(String name);
    void add(String name,String macaddress);
}
