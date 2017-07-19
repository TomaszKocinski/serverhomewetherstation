package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.service.SensorDataServiceImpl;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private SensorDataServiceImpl sensorDataService;
    @RequestMapping(value = "/data/{val}/dev/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sendData(
            @PathVariable("val") Integer value, @PathVariable("name") String name) {
        sensorDataService.addData(value,name);
        return "Data:" + value +" from dev:" + name + " is recived by server";
    }
}
