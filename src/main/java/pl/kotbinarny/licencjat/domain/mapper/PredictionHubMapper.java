package pl.kotbinarny.licencjat.domain.mapper;

import pl.kotbinarny.licencjat.domain.PredictionHub;
import pl.kotbinarny.licencjat.domain.Sensor;

public class PredictionHubMapper {

    public static PredictionHub map(Sensor sensor){
        PredictionHub predictionHub = new PredictionHub();
        predictionHub.setSensor(sensor);
        return predictionHub;
    }

}
