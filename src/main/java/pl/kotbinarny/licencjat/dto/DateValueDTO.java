package pl.kotbinarny.licencjat.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class DateValueDTO {
    Long date;
    BigDecimal value;
}
