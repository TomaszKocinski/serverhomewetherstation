package pl.kotbinarny.licencjat.domain;


import lombok.*;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by tkocinski on 18.07.2017.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class Prediction {
    
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    Long idPreduction;
    
    @NonNull
    BigDecimal tempPred;
    @NonNull
    BigDecimal pressurePred;
    @NonNull
    BigDecimal humidityPred;
    @NonNull
    BigDecimal lightPred;
    @NonNull
    LocalDateTime date;

    @ManyToOne
    PredictionHub predictionHub;

}
