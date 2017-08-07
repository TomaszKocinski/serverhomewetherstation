package pl.kotbinarny.licencjat.utills;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class DataBySensor {
    List<String> date;
    List<BigDecimal> value;
    String sensor;
}
