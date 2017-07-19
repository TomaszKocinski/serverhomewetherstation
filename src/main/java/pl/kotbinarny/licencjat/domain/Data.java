package pl.kotbinarny.licencjat.domain;

import lombok.*;


import javax.persistence.*;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="idSensorData")
@EqualsAndHashCode(exclude="idSensorData")
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer idSensorData;
    String data;
    Integer value;
    @ManyToOne
    @JoinColumn(name = "idSensor")
    Sensor Sensor;
}
