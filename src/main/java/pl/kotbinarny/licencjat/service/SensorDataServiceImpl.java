package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kotbinarny.licencjat.dao.SensorDataDao;
import pl.kotbinarny.licencjat.domain.SensorData;
import pl.kotbinarny.licencjat.service.api.SensorDataService;

import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Service
public class SensorDataServiceImpl implements SensorDataService{
    @Autowired
    SensorDataDao sensorDataDao;

    @Override
    public List<SensorData> findAll(){
        return sensorDataDao.findAll();
    }
}
