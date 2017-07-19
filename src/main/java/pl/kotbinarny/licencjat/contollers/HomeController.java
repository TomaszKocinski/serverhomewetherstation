package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.service.SensorDataServiceImpl;


import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private SensorDataServiceImpl sensorDataService;


    @RequestMapping
    public List<Data> home() {
        return sensorDataService.findAll();
    }
    @RequestMapping("/dodajprzy")
    public String dodajPrzykladoweRekordy() {
        sensorDataService.addData(2,"test");
        sensorDataService.addData(12,"test");
        return "ToDo";
    }
    @RequestMapping("/hello")
    public String hello() {
        return "redirect://www.wp.pl";
    }
    @RequestMapping("/espHello")
    public String espHello() {
        return "hello";
    }
    @RequestMapping(value = "/ex/foos/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFoosBySimplePathWithPathVariable(
            @PathVariable("id") long id) {
        return "Get a specific Foo with id=" + id*2;
    }


}