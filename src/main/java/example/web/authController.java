package example.web;

import example.config.Access;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class authController
{
    // 配置注解权限, 允许身份为admin, 或者说允许权限为admin的人访问
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @Access(authorities = {"user/list"})
    public String admin() {
        return "login/admin";
    }

    @RequestMapping(value = "/noAuth", method = RequestMethod.GET)
    public String noAuth() {
        return "login/noAuth";
    }

}
