package pl.kotbinarny.licencjat.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Transient;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@ToString(exclude = "sensor")
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(indexes = { @Index(name = "IDX_MYIDX1", columnList = "date,idSensor", unique = true) })
public class Data {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idData;

    LocalDateTime date;

    @NonNull
    BigDecimal temp;
    @NonNull
    BigDecimal pressure;
    @NonNull
    BigDecimal humidity;
    @NonNull
    BigDecimal light;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "idSensor")
    Sensor sensor;

    @Transient
    @PrePersist
    protected void onCreate() {
        date=LocalDateTime.now();
    }

}
