package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kotbinarny.licencjat.domain.SensorData;
import pl.kotbinarny.licencjat.service.SensorDataServiceImpl;
import pl.kotbinarny.licencjat.service.api.SensorDataService;


import java.util.List;

@RestController
@RequestMapping("/")
public class AppCont {
    @Autowired
    private SensorDataServiceImpl sensorDataService;

    @RequestMapping
    public List<SensorData> home() {
        return sensorDataService.findAll();
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


}