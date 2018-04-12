package example.web;

import example.domain.User;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list/all")
    public List<User> listAll() {
        return userService.listAll();
    }

    @RequestMapping("/page1")
    public String page2(Model model) {
        model.addAttribute("content", "2：通过接收Model对象，设置属性的方式传递给jsp页面");
        return "page1";
    }
}