package pl.kotbinarny.licencjat.domain.mapper;

import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.service.weka.InstanceOrder;

import java.math.BigDecimal;

public class PredictionMapper {

    public static Prediction map(BigDecimal temp, BigDecimal pressure, BigDecimal hum, BigDecimal light){
        Prediction prediction = new Prediction();
        prediction.setTempPred(temp);
        prediction.setPressurePred(pressure);
        prediction.setHumidityPred(hum);
        prediction.setLightPred(light);
        return prediction;
    }
    public static void mapByEnum(Prediction prediction, InstanceOrder instanceOrder, double value){
        BigDecimal valueBigDecimal = BigDecimal.valueOf(value);
        switch (instanceOrder){
            case TEMPERATURE:
                prediction.setTempPred(valueBigDecimal);
                break;
            case PRESSURE:
                prediction.setPressurePred(valueBigDecimal);
                break;
            case HUMIDITY:
                prediction.setHumidityPred(valueBigDecimal);
                break;
            case LIGHT:
                prediction.setLightPred(valueBigDecimal);
                break;
            default:
                throw new RuntimeException("Add value to this mapper, you forgot about: " + instanceOrder.name() );
        }
    }

}
