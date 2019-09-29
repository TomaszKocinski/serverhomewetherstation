package pl.kotbinarny.licencjat.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class DataAndPredictionBySensorDTO {
    List<DateValueDTO> dateValue;
    List<PredictionDto> predictionValue;
    String sensor;
}
