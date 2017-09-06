package pl.kotbinarny.licencjat.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Transient;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode()
@Entity
public class TemperatureData {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idData;

    @Transient
    LocalDateTime date;
    
    @NonNull
    BigDecimal value;
    
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
