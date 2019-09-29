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
public interface PredictionDao extends JpaRepository<Prediction,Integer> {
    List<Prediction> findByPredictionHub(PredictionHub predictionHub);

}
