package pl.kotbinarny.licencjat.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.kotbinarny.licencjat.domain.Sensor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class TemperatureSensorFromToDTO {
    Sensor sensor;
    LocalDateTime from;
    LocalDateTime to;
    List<DateValueDTO> dateValue=new ArrayList<>();
}
