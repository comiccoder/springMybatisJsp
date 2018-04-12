package example.web;

import example.domain.User;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public ModelAndView listAll()
    {
        ModelMap modelMap = new ModelMap();
        List <User>  returnUser = userService.listAll();
        //要返回的数据
        modelMap.addAttribute("list",returnUser);

        return new ModelAndView("userList", modelMap);
    }

    @RequestMapping("/page1")
    public String page2(Model model) {
        model.addAttribute("content", "2：通过接收Model对象，设置属性的方式传递给jsp页面");
        return "page1";
    }
}