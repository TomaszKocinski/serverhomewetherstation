package pl.kotbinarny.licencjat.service.weka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.kotbinarny.licencjat.dao.DataDao;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.service.PredictionHubService;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static pl.kotbinarny.licencjat.service.weka.InstanceOrder.*;

@Component
@Slf4j
public class PredictionWeka {

    private final DataDao dataDao;
    private final PredictionHubService predictionHubService;
    private final ForecastingTimeSeries forecastingTimeSeries;

    @Autowired
    public PredictionWeka(DataDao dataDao, PredictionHubService predictionHubService, ForecastingTimeSeries forecastingTimeSeries) {
        this.dataDao = dataDao;
        this.predictionHubService = predictionHubService;
        this.forecastingTimeSeries = forecastingTimeSeries;
    }

    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    @Transactional
    public void scheduldedPredictions() {
        log.info("start schedulde task");
        List<Data> dateFromAllSensors = dataDao.findByDateAfterOrderByDateAsc(LocalDateTime.now().minusMinutes(25));
        Map<Sensor, List<Data>> dataGroupedBySensor = dateFromAllSensors.stream()
                .collect(Collectors.groupingBy(Data::getSensor));
        dataGroupedBySensor.entrySet()
                .parallelStream()
                .forEach(predictAndSave());
    }

    private Consumer<Map.Entry<Sensor, List<Data>>> predictAndSave() {
        return sensorListEntry -> {
            List<Prediction> predictions = predicteFromData(sensorListEntry.getValue());
            predictionHubService.save(predictions, sensorListEntry.getKey());
        };
    }

    private List<Prediction> predicteFromData(List<Data> all) {
        ArrayList<Attribute> atts = createAtrributes();

        Instances dataRaw = new Instances("TestInstances", atts, 0);

        all.forEach(data -> {
            double[] instanceValue = new double[dataRaw.numAttributes()];
            instanceValue[TEMPERATURE.ordinal()] = data.getTemp().doubleValue();
            instanceValue[PRESSURE.ordinal()] = data.getPressure().doubleValue();
            instanceValue[HUMIDITY.ordinal()] = data.getHumidity().doubleValue();
            instanceValue[LIGHT.ordinal()] = data.getLight().doubleValue();
            instanceValue[DATE.ordinal()] = data.getDate().atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
            dataRaw.add(new DenseInstance(1.0, instanceValue));
        });
        printDebug(dataRaw);
        return forecastingTimeSeries.calculate(dataRaw);

    }

    private void printDebug(Instances dataRaw) {
        log.info(dataRaw.toString());
    }

    private ArrayList<Attribute> createAtrributes() {
        ArrayList<Attribute> atts = new ArrayList<>();
        atts.add(new Attribute(TEMPERATURE.name()));
        atts.add(new Attribute(PRESSURE.name()));
        atts.add(new Attribute(HUMIDITY.name()));
        atts.add(new Attribute(LIGHT.name()));
        atts.add(new Attribute(getDate(), (String) null));
        return atts;
    }


}
