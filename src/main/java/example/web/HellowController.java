package example.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@EnableAutoConfiguration
public class HellowController {

    @RequestMapping("/hellow")
    public ModelAndView getListaUtentiView(){
        ModelMap model = new ModelMap();
        model.addAttribute("name", "Spring Boot");
        return new ModelAndView("hellow", model);
    }
}




