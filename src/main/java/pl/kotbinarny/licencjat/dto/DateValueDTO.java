package pl.kotbinarny.licencjat.dto;

import lombok.*;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.domain.Prediction;

import java.math.BigDecimal;
import java.time.ZoneOffset;

@Setter
@Getter
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class DateValueDTO {
    Long date;
    BigDecimal temperature;
    BigDecimal pressure;
    BigDecimal humidity;
    BigDecimal light;

    public DateValueDTO(Data data) {
        this.date = data.getDate().toEpochSecond(ZoneOffset.ofHours(1)) * 1000;
        this.temperature = data.getTemp();
        this.pressure = data.getPressure();
        this.humidity = data.getHumidity();
        this.light = data.getLight();
    }

    public DateValueDTO(Prediction data) {
        this.date = data.getDate().toEpochSecond(ZoneOffset.ofHours(1)) * 1000;
        this.temperature = data.getTempPred();
        this.pressure = data.getPressurePred();
        this.humidity = data.getHumidityPred();
        this.light = data.getLightPred();
    }
}
