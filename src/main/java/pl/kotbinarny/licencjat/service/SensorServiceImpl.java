package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kotbinarny.licencjat.dao.SensorDao;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.service.api.SensorService;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    @Autowired
    SensorDao sensorDao;

    @Override
    public List<Sensor> findAll() {
        return sensorDao.findAll();
    }

    @Override
    public Sensor findByName(String name) {
        return sensorDao.findByName(name);
    }

    @Override
    public Boolean addIfNotExist(String name, String macaddress) {
        if (sensorDao.findByName(name) != null) {
            return true;
        }
        sensorDao.save(new Sensor(null, name, macaddress));
        return false;
    }
}
