package example.web;

import example.entity.*;
import example.service.PermissionService;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RegisterController
{
    @Autowired
    UserService userService;

    @RequestMapping(value="noAuth/register",method = RequestMethod.GET)
    public String noAuthRegister()
    {
        return "noAuth/register";
    }


    //发起请求
    @ResponseBody
    @RequestMapping(value="noAuth/register",method = RequestMethod.POST)
    public JResult register(User user,
                           HttpSession session,
                           HttpServletRequest request,
                           HttpServletResponse response)
    {
        JResult result=new JResult();

        try {
            //获取返回的user
            User returnUser = userService.findUserByName(user);

            if (returnUser != null) {
                result.setCode(StatusCode.FAIL.getValue()).
                        setContent(null).
                        setMsg("用户名已存在");
                return result;
            }

            //保存用户信息到数据库
            userService.addUser(user);
            result.setCode(
                    StatusCode.OK.getValue()).
                    setContent(null).
                    setMsg("注册用户成功，请登陆");


        }catch (Exception e)
        {
            e.printStackTrace();
            result.setCode(
                    StatusCode.FAIL.getValue()).
                    setContent(null).
                    setMsg("注册用户异常");
        }
        return  result;
    }

}

