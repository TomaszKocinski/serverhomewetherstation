package pl.kotbinarny.licencjat.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="idStatistics")
@EqualsAndHashCode(exclude="idStatistics")
@Entity
public class Statistics {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idStatistics;
    Long value;
    String type;
    Date dateFrom;
    Date dateTo;
    @ManyToOne
    @JoinColumn(name = "idSensor")
    Sensor Sensor;
}