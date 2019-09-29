package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.dto.DataAndPredictionBySensorDTO;
import pl.kotbinarny.licencjat.dto.TemperatureBySensorFromToDTO;
import pl.kotbinarny.licencjat.service.api.TemperatureDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private TemperatureDataService temperatureDataService;

    @RequestMapping(value = "/{temp}/{pressure}/{humidity}/{light}/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sendDataBMP280(
            @PathVariable("temp") BigDecimal value, @PathVariable BigDecimal humidity, @PathVariable BigDecimal pressure, @PathVariable BigDecimal light, @PathVariable("name") String name) {
        return temperatureDataService.addData(value, humidity, pressure, light, name).toString();
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
    public List<Data> getAll() {
        return temperatureDataService.findAll();
    }

    @RequestMapping("/bySensor")
    @ResponseBody
    public List<DataAndPredictionBySensorDTO> getAllbySensor() {
        return temperatureDataService.findAllSortedBySensor();
    }


}
