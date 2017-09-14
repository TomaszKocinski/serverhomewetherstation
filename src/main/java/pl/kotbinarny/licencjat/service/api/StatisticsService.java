package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.Sensor;

import java.time.LocalDateTime;

public interface StatisticsService {
    void meanDaily(Sensor sensor, LocalDateTime from, LocalDateTime to);

    void maxDaily(Sensor sensor, LocalDateTime from, LocalDateTime to);

    void minDaily(Sensor sensor, LocalDateTime from, LocalDateTime to);
}
