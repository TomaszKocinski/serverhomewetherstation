package pl.kotbinarny.licencjat.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kotbinarny.licencjat.domain.Data;
import pl.kotbinarny.licencjat.service.SensorDataServiceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private SensorDataServiceImpl sensorDataService;


    @RequestMapping("/greeting")
    public String home(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greetings";
    }
    /*@GetMapping("/data")
    public String home(Model model){
        Map<String,Long> mapa = new HashMap<>();
        for (Data i : sensorDataService.findAll()){
            mapa.put(i.getData(),i.getValue().longValue());
        }
        model.addAttribute("mapa",mapa);
        return "index";
    }*/


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