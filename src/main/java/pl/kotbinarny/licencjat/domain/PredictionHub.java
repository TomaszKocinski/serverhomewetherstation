package pl.kotbinarny.licencjat.domain;


import lombok.*;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@ToString(exclude = "sensor")
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class PredictionHub {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long idPredictionHub;

    @NonNull
    Boolean newest;

    LocalDateTime dateOfInsert;
    LocalDateTime dateOfUpdate;

    @NonNull
    @OneToOne()
    @JoinColumn(name = "idSensor")
    Sensor sensor;

    @Transient
    @PrePersist
    protected void onCreate() {
        dateOfInsert = LocalDateTime.now();
        dateOfUpdate = LocalDateTime.now();
        newest = Boolean.TRUE;
    }

    @Transient
    @PreUpdate
    protected void onUpdate() {
        dateOfUpdate = LocalDateTime.now();
    }


}
