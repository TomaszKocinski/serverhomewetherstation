package pl.kotbinarny.licencjat.dto;

import lombok.*;
import pl.kotbinarny.licencjat.domain.Prediction;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class PredictionDto {
    List<DateValueDTO> dateValue;
    String nameOfPredictionMethod;

    public PredictionDto(List<Prediction> predictions, String nameOfPredictionMethod) {
        this.dateValue = predictions.stream()
               .map(DateValueDTO::new)
               .collect(Collectors.toList());
        this.nameOfPredictionMethod = nameOfPredictionMethod;
    }
}
