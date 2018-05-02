package example.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import example.entity.User;
import javax.servlet.http.HttpSession;


@Controller
@EnableAutoConfiguration
public class HellowController {

    @RequestMapping(value={"/","/index"})
    public String index(){
        return "redirect:noAuth/listPromotionPage";
    }

    @RequestMapping("/hellow_1")
    public ModelAndView hellow_1(){
        ModelMap model = new ModelMap();
        model.addAttribute("name", "张三");
        return new ModelAndView("hellow", model);
    }

    @RequestMapping("/hellow")
    public String hellow(HttpSession session,ModelMap model)
    {
        User myUser=(User)session.getAttribute("user");
        if(myUser==null)
        {
            return "redirect:user/login";
        }

        //ModelMap model = new ModelMap();
        model.addAttribute("name", "张三");
        //return new ModelAndView("hellow", model);

        return "hellow";
    }
}




