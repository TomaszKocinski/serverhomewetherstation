package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.service.SensorServiceImpl;

@RestController
@RequestMapping("/Sensor")
public class SensorController {

    private final SensorServiceImpl sensorServiceImpl;

    public SensorController(SensorServiceImpl sensorServiceImpl) {
        this.sensorServiceImpl = sensorServiceImpl;
    }

    @RequestMapping(value = "/newDev/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String newDev(
            @PathVariable("name") String name) {
        Boolean aBoolean = sensorServiceImpl.addIfNotExist(name, null);
        if (aBoolean) {
            return "Device:" + name + "is already added to server, nothing was done";
        }
        return "Device:" + name + "is added to server.";
    }
}
