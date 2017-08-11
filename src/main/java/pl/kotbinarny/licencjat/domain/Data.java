package pl.kotbinarny.licencjat.domain;

import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long idData;
    String date;
    BigDecimal value;
    @ManyToOne()
    @JoinColumn(name = "idSensor")
    Sensor sensor;
}
