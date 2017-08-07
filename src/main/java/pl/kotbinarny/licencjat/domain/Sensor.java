package pl.kotbinarny.licencjat.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by tkocinski on 19.07.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="idSensor")
@EqualsAndHashCode(exclude="idSensor")
@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idSensor;
    @Column(unique=true)
    String name;
    @Column(unique=true)
    String macaddress;
}