package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kotbinarny.licencjat.dao.SensorDao;
import pl.kotbinarny.licencjat.dao.DataDao;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.service.api.DataService;
import pl.kotbinarny.licencjat.utills.DataBySensor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataDao dataDao;
    @Autowired
    SensorDao sensorDao;

    @Override
    public List<Data> findAll(){
        return dataDao.findAll();
    }
    @Override
    public void addData(BigDecimal value, String NameOfSensor){
        dataDao.save(new Data(null,new Date().toString(),value,sensorDao.findByName(NameOfSensor)));
    }
    /*public List<DataBySensor> findAllSortedBySensor(){
        List<DataBySensor> dataBySensor=new LinkedList<>();
        List<Sensor> sensors = sensorDao.findAll();
        for(Sensor sensor:sensors){
            List<Data> databysensortemp= dataDao.findAllBySensor(sensor.getIdSensor());
            if(!databysensortemp.isEmpty()){
                List<String> date=new LinkedList<>();
                List<BigDecimal> value=new LinkedList<>();
                for (Data dataelement:databysensortemp){
                    date.add(dataelement.getData());
                    value.add(dataelement.getValue());
                }
                dataBySensor.add(new DataBySensor(date,value,sensor.getName()));
            }
        }
        return null;
    }*/

}
