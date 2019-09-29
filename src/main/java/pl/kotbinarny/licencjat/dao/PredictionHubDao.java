package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.domain.PredictionHub;
import pl.kotbinarny.licencjat.domain.Sensor;

import java.util.List;


@Repository
public interface PredictionHubDao extends JpaRepository<PredictionHub,Integer> {
    List<PredictionHub> findBySensorAndNewestTrue(Sensor sensor);

    @Query("SELECT p FROM PredictionHub ph INNER JOIN Prediction p on p.predictionHub = ph.id where  ph.newest= true and ph.sensor = ?1 ")
    List<Prediction> findByPredictionHub(Sensor sensor);



    @Query("update PredictionHub set newest = ?1 where idPredictionHub <> ?2 and sensor.idSensor = ?3")
    @Modifying
    @Transactional
    void updateEnsureOnlyOneNewest(Boolean aBoolean, Long insertedRecord, Long sensor);

}
