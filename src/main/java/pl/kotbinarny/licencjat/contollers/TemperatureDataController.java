package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.service.TemperatureDataServiceImpl;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/data")
public class TemperatureDataController {
    @Autowired
    private TemperatureDataServiceImpl dataService;
    @RequestMapping(value = "/data/{val}/dev/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sendData(
            @PathVariable("val") BigDecimal value, @PathVariable("name") String name) {
        dataService.addData(value,name);
        return "TemperatureData:" + value +" from dev:" + name + " is recived by server";
    }
    @RequestMapping
    @ResponseBody
    public List<TemperatureData> getAll() {
        return dataService.findAll();
    }
    @RequestMapping("/bySensor")
    @ResponseBody
    public List<TemperatureBySensorDTO> getAllbySensor() {
        return dataService.findAllSortedBySensor();
    }
}
