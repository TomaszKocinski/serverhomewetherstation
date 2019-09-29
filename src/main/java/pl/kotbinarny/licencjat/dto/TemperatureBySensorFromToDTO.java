package pl.kotbinarny.licencjat.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class TemperatureBySensorFromToDTO {
    LocalDateTime from;
    LocalDateTime to;
    List<DataAndPredictionBySensorDTO> dataAndPredictionBySensorDTOList =new ArrayList<>();
}
