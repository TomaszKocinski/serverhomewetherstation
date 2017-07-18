package pl.kotbinarny.licencjat.domain;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
@Entity
public class SensorData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String data;
    Integer value;
    Integer numberOfSensor;
}
