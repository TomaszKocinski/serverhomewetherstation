package pl.kotbinarny.licencjat.domain.mapper;

import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.domain.Sensor;

import java.math.BigDecimal;

public class DataMapper {

    public static Data map(BigDecimal value, BigDecimal pressure, BigDecimal hum, BigDecimal light, Sensor sensor){
        Data data = new Data();
        data.setTemp(value);
        data.setPressure(pressure);
        data.setHumidity(hum);
        data.setLight(light);
        data.setSensor(sensor);
        return data;
    }



}
