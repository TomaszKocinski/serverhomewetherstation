package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorFromToDTO;
import pl.kotbinarny.licencjat.service.api.TemperatureDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/data")
public class TemperatureDataController {
    @Autowired
    private TemperatureDataService temperatureDataService;


    @RequestMapping(value = "/{val}/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sendData(
            @PathVariable("val") BigDecimal value, @PathVariable("name") String name) {
        temperatureDataService.addData(value, name);
        return "TemperatureData:" + value + " from dev:" + name + " is recived by server";
    }

    @RequestMapping(value = "/from/{from}/to/{to}", method = RequestMethod.GET)
    @ResponseBody
    public TemperatureBySensorFromToDTO getTemperatureBySensorFromToWS(
            @PathVariable("from") String fromString, @PathVariable("to") String toString) {

        LocalDateTime from = LocalDateTime.parse(fromString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime to = LocalDateTime.parse(toString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return temperatureDataService.findAllSortedBySensorHighAndLowerDate(from, to);
    }

    @RequestMapping
    @ResponseBody
    public List<TemperatureData> getAll() {
        return temperatureDataService.findAll();
    }

    @RequestMapping("/bySensor")
    @ResponseBody
    public List<TemperatureBySensorDTO> getAllbySensor() {
        return temperatureDataService.findAllSortedBySensor();
    }


}
