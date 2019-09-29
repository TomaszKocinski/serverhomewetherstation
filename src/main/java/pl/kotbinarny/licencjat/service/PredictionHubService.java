package pl.kotbinarny.licencjat.service;

import org.springframework.stereotype.Service;
import pl.kotbinarny.licencjat.dao.PredictionDao;
import pl.kotbinarny.licencjat.dao.PredictionHubDao;
import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.domain.PredictionHub;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.mapper.PredictionHubMapper;

import java.util.List;

@Service
public class PredictionHubService {

    private final PredictionHubDao predictionHubDao;
    private final PredictionDao predictionDao;

    public PredictionHubService(PredictionHubDao predictionHubDao, PredictionDao predictionDao) {
        this.predictionHubDao = predictionHubDao;
        this.predictionDao = predictionDao;
    }

    public PredictionHub save(List<Prediction> predictions, Sensor sensor){
        PredictionHub newHub = PredictionHubMapper.map(sensor);
        predictionHubDao.saveAndFlush(newHub);
        predictions.forEach(entity -> {
            entity.setPredictionHub(newHub);
            predictionDao.save(entity);
        });
        predictionHubDao.updateEnsureOnlyOneNewest(Boolean.FALSE, newHub.getIdPredictionHub(), sensor.getIdSensor());
        return newHub;
    }
}
