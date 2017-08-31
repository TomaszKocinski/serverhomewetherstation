package pl.kotbinarny.licencjat.contollers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pl.kotbinarny.licencjat.service.DataServiceImpl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private DataServiceImpl sensorDataService;


    @RequestMapping("/greeting")
    public String home(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        String value= "dsada";
        model.addAttribute("value",value);
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
        sensorDataService.addData(BigDecimal.valueOf(2),"test");
        sensorDataService.addData(BigDecimal.valueOf(12),"test");
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

    /*@RequestMapping("/person")
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        DataModel person = new DataModel();
        person.setName("Person's name");
        Gson gson = new Gson();

        ModelAndView modelAndView = new ModelAndView("DataModel");
        modelAndView.addObject("DataModel", gson.toJson(person));

        return modelAndView;
    }*/
}