package pl.kotbinarny.licencjat.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "idStatistics")
@EqualsAndHashCode(exclude = "idStatistics")
@Entity
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long idStatistics;
    BigDecimal value;
    String type;
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
    @ManyToOne
    @JoinColumn(name = "idSensor")
    Sensor sensor;

    public Statistics(String type, LocalDateTime dateFrom, LocalDateTime dateTo, Sensor sensor) {
        this.type = type;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.sensor = sensor;
    }
}