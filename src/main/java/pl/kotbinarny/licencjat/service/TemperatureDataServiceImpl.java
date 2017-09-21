package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kotbinarny.licencjat.dao.SensorDao;
import pl.kotbinarny.licencjat.dao.TemperatureDataDao;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.dto.DateValueDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorFromToDTO;
import pl.kotbinarny.licencjat.service.api.StatisticsService;
import pl.kotbinarny.licencjat.service.api.TemperatureDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Service
@Transactional
public class TemperatureDataServiceImpl implements TemperatureDataService {
    @Autowired
    private TemperatureDataDao temperatureDataDao;
    @Autowired
    private SensorDao sensorDao;
    @Autowired
    private StatisticsService statisticsService;

    @Override
    public List<TemperatureData> findAll() {
        return temperatureDataDao.findAll();
    }

    @Override
    public void addData(BigDecimal value, String NameOfSensor) {
        Sensor sensor = sensorDao.findByName(NameOfSensor);
        temperatureDataDao.save(new TemperatureData(value, sensor));
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        statisticsService.maxDaily(sensor, today, today.plusDays(1));
        statisticsService.minDaily(sensor, today, today.plusDays(1));
        statisticsService.meanDaily(sensor, today, today.plusDays(1));
    }

    @Override
    public List<TemperatureBySensorDTO> findAllSortedBySensor() {
        List<TemperatureBySensorDTO> temperatureBySensor = new LinkedList<>();
        List<Sensor> sensors = sensorDao.findAll();
        for (Sensor sensor : sensors) {
            temperatureDataDao.findBySensor(sensor);
            List<TemperatureData> tempBySensorTemp = temperatureDataDao.findBySensor(sensor);
            if (!tempBySensorTemp.isEmpty()) {
                List<DateValueDTO> dateValue = new ArrayList<>();
                for (TemperatureData tempElement : tempBySensorTemp) {
                    dateValue.add(new DateValueDTO(tempElement.getDate().toEpochSecond(ZoneOffset.ofHours(1)) * 1000, tempElement.getValue()));
                }
                temperatureBySensor.add(new TemperatureBySensorDTO(dateValue, sensor.getName()));
                dateValue.sort(((o1, o2) -> (o1.getDate() > o2.getDate()) ? 1 : -1));
            }
        }
        return temperatureBySensor;
    }

    public TemperatureBySensorFromToDTO findAllSortedBySensorHighAndLowerDate(LocalDateTime from, LocalDateTime to) {
        TemperatureBySensorFromToDTO temperatureBySensor = new TemperatureBySensorFromToDTO();
        temperatureBySensor.setFrom(from);
        temperatureBySensor.setTo(to);
        List<Sensor> sensors = sensorDao.findAll();
        for (Sensor sensor : sensors) {
            //temperatureDataDao.findBySensor(sensor);
            List<TemperatureData> tempBySensorTemp = temperatureDataDao.findBySensorAndDateGreaterThanAndDateLessThanEqual(sensor, from, to);
            if (!tempBySensorTemp.isEmpty()) {
                List<DateValueDTO> dateValue = new ArrayList<>();
                for (TemperatureData tempElement : tempBySensorTemp) {
                    dateValue.add(new DateValueDTO(tempElement.getDate().toEpochSecond(ZoneOffset.ofHours(1)) * 1000, tempElement.getValue()));
                }
                temperatureBySensor.getTemperatureBySensorDTOList().add(new TemperatureBySensorDTO(dateValue, sensor.getName()));
                dateValue.sort(((o1, o2) -> (o1.getDate() > o2.getDate()) ? -1 : 1));
            }
        }
        return temperatureBySensor;


    }

    @Override
    public List<DateValueDTO> findAllBySensorHighAndLowerDate(Sensor sensor, LocalDateTime from, LocalDateTime to) {
        List<DateValueDTO> dateValue = new ArrayList<>();
        List<TemperatureData> tempBySensorTemp = temperatureDataDao.findBySensorAndDateGreaterThanAndDateLessThanEqual(sensor, from, to);
        if (!tempBySensorTemp.isEmpty()) {

            for (TemperatureData tempElement : tempBySensorTemp) {
                dateValue.add(new DateValueDTO(tempElement.getDate().toEpochSecond(ZoneOffset.ofHours(1)) * 1000, tempElement.getValue()));
            }
        }
        return dateValue;
    }


}
