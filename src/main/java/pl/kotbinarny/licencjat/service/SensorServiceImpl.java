package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kotbinarny.licencjat.dao.SensorDao;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.service.api.SensorService;

import java.util.List;
import java.util.Timer;
@Service
public class SensorServiceImpl implements SensorService{
    @Autowired
    SensorDao sensorDao;

    @Override
    public List<Sensor> findAll(){
        return sensorDao.findAll();
    }
    @Override
    public Sensor findByName(String name){
        return sensorDao.findByName(name);
    }
    @Override
    public void add(String name){
        if (sensorDao.findByName(name) != null) System.out.println("addsensor: name of sensor is already in database, TODO look at this behaviour");
        sensorDao.save(new Sensor(null,name));
    }
}
