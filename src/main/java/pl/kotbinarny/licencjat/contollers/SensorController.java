package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.service.SensorServiceImpl;

@RestController
@RequestMapping("/Sensor")
public class SensorController {
    @Autowired
    private SensorServiceImpl sensorServiceImpl;
    @RequestMapping(value = "/newDev/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String newDev(
            @PathVariable("name") String name) {
        sensorServiceImpl.add(name);
        return "Device:" + name + "is added to server/ check sout";
    }
}
