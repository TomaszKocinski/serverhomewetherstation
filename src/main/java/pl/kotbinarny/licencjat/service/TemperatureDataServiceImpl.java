package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kotbinarny.licencjat.dao.DataDao;
import pl.kotbinarny.licencjat.dao.PredictionHubDao;
import pl.kotbinarny.licencjat.dao.SensorDao;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.mapper.DataMapper;
import pl.kotbinarny.licencjat.dto.DateValueDTO;
import pl.kotbinarny.licencjat.dto.PredictionDto;
import pl.kotbinarny.licencjat.dto.DataAndPredictionBySensorDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorFromToDTO;
import pl.kotbinarny.licencjat.service.api.TemperatureDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Service
@Transactional
public class TemperatureDataServiceImpl implements TemperatureDataService {
    @Autowired
    private DataDao dataDao;
    @Autowired
    private SensorDao sensorDao;
    @Autowired
    private PredictionHubDao predictionHubDao;

    @Override
    public List<Data> findAll() {
        return dataDao.findAll();
    }

    @Override
    public Data addData(BigDecimal value, BigDecimal hum, BigDecimal pressure, BigDecimal light, String name) {
        Sensor sensor = sensorDao.findByName(name);
        return dataDao.save(DataMapper.map(value, pressure, hum, light, sensor));
    }

    @Override
    public List<DataAndPredictionBySensorDTO> findAllSortedBySensor() {
        List<DataAndPredictionBySensorDTO> temperatureBySensor = new LinkedList<>();
        List<Sensor> sensors = sensorDao.findAll();
        for (Sensor sensor : sensors) {
            dataDao.findBySensor(sensor);
            List<Data> tempBySensorTemp = dataDao.findBySensor(sensor);

            if (!tempBySensorTemp.isEmpty()) {
                List<DateValueDTO> trueValues = tempBySensorTemp.stream()
                        .map(DateValueDTO::new)
                        .collect(Collectors.toList());
                List<Prediction> byPredictionHub = predictionHubDao.findByPredictionHub(sensor);
                List<PredictionDto> predictedValues = new ArrayList<>();
                predictedValues.add(new PredictionDto(byPredictionHub, "Gausse"));

                trueValues.sort(((o1, o2) -> (o1.getDate() > o2.getDate()) ? 1 : -1));
                predictedValues
                        .forEach(predictionDto -> predictionDto.getDateValue()
                                .sort(((o1, o2) -> (o1.getDate() > o2.getDate()) ? 1 : -1)));

                temperatureBySensor.add(new DataAndPredictionBySensorDTO(trueValues, predictedValues, sensor.getName()));
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
            List<Data> tempBySensorTemp = dataDao.findBySensorAndDateGreaterThanAndDateLessThanEqual(sensor, from, to);
            if (!tempBySensorTemp.isEmpty()) {
                List<DateValueDTO> dateValue = new ArrayList<>();
                for (Data tempElement : tempBySensorTemp) {
                    dateValue.add(new DateValueDTO(tempElement));
                }
//                temperatureBySensor.getTemperatureBySensorDTOList().add(new TemperatureBySensorDTO(dateValue, sensor.getName()));
                dateValue.sort(((o1, o2) -> (o1.getDate() > o2.getDate()) ? -1 : 1));
            }
        }
        return temperatureBySensor;


    }

    @Override
    public List<DateValueDTO> findAllBySensorHighAndLowerDate(Sensor sensor, LocalDateTime from, LocalDateTime to) {
        List<DateValueDTO> dateValue = new ArrayList<>();
        List<Data> tempBySensorTemp = dataDao.findBySensorAndDateGreaterThanAndDateLessThanEqual(sensor, from, to);
        if (!tempBySensorTemp.isEmpty()) {

            for (Data tempElement : tempBySensorTemp) {
                dateValue.add(new DateValueDTO(tempElement));
            }
        }
        return dateValue;
    }


}
