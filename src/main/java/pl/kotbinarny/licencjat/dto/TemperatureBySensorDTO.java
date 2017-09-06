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
public class TemperatureBySensorDTO {
    List<DateValueDTO> dateValue;
    String sensor;
}
