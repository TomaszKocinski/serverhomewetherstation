package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.service.DataServiceImpl;
import pl.kotbinarny.licencjat.utills.DataBySensor;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataServiceImpl dataService;
    @RequestMapping(value = "/data/{val}/dev/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sendData(
            @PathVariable("val") BigDecimal value, @PathVariable("name") String name) {
        dataService.addData(value,name);
        return "Data:" + value +" from dev:" + name + " is recived by server";
    }
    @RequestMapping
    @ResponseBody
    public List<Data> getAll() {
        return dataService.findAll();
    }
    /*@RequestMapping("/bySensor")
    @ResponseBody
    public List<DataBySensor> getAllbySensor() {
        return dataService.findAllSortedBySensor();
    }*/
}
