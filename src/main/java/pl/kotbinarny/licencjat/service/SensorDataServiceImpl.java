package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kotbinarny.licencjat.dao.SensorDao;
import pl.kotbinarny.licencjat.dao.DataDao;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.service.api.SensorDataService;

import java.util.Date;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Service
public class SensorDataServiceImpl implements SensorDataService{
    @Autowired
    DataDao dataDao;
    @Autowired
    SensorDao sensorDao;

    @Override
    public List<Data> findAll(){
        return dataDao.findAll();
    }
    @Override
    public void addData(Integer value,String NameOfSensor){
        dataDao.save(new Data(null,new Date().toString(),value,sensorDao.findByName(NameOfSensor)));
    }
}
