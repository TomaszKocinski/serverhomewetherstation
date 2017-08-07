package pl.kotbinarny.licencjat.service.api;

import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.utills.DataBySensor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface DataService {
    List<Data> findAll();
    void addData(BigDecimal value, String NameOfSensor);
    //List<DataBySensor> findAllSortedBySensor();
}
