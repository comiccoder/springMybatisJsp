package example.web;

import example.entity.JResult;
import example.entity.Permission;
import example.entity.StatusCode;
import example.entity.User;
import example.service.PermissionService;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LoginController
{
    @Autowired
    UserService userService;

    @Autowired
    private PermissionService permissionService;

    /******************************************
     //***login相关 @此段代码为杨松老师编写，点赞
     //点在人：李xx
     *****************************************/
    @RequestMapping(value="noAuth/login01",method = RequestMethod.GET)
    public String login01()
    {
        return "noAuth/login_01";
    }

    @RequestMapping(value="noAuth/login",method = RequestMethod.GET)
    public String login()
    {
        return "noAuth/login";
    }

    //发起请求
    //采用jsson的方式，，如果出错
    @ResponseBody
    @RequestMapping(value="noAuth/login",method = RequestMethod.POST )
    public JResult login(       User user,
                               HttpSession session,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
        User myUser = null;
        List<Permission> perms=new ArrayList<Permission>();
        Set<Permission> perms_set=new HashSet<Permission>();
        JResult result= new JResult();   //

        if(user!=null)
        {
            myUser=userService.selectUserByName(user.getUserName(),user.getPassword());

            if(myUser!=null)
            {
                session.setAttribute("user",myUser);

                perms= permissionService.getPermissionByUserId(myUser.getId());

                Set<String> perms_str= new HashSet<String>();

                for(Permission perm:perms)
                {
                    perms_str.add(perm.getPermission());
                }

                session.setAttribute("perms",perms_str);

                result.setCode(
                        StatusCode.OK.getValue()).
                        setContent(null).
                        setMsg("登陆成功，跳转");

                return result   ;   // "redirect:../noAuth/listPromotionPage";
            }else
            {
                result.setCode(
                        StatusCode.failCode.getValue()).
                        setContent(null).
                        setMsg("登陆失败，请核对账号密码");

                return result;
            }
        }else {
            result.setCode(
                    StatusCode.failCode.getValue()).
                    setContent(null).
                    setMsg("传入有问题，请核对");
            return result;
        }
    }


    //注销
    @ResponseBody
    @RequestMapping(value="noAuth/loginOut",method = RequestMethod.POST )
    public JResult loginOut(
                                 HttpSession session,
                                 HttpServletRequest request,
                                 HttpServletResponse response   )
    {
        JResult jResult=new JResult();
        session.removeAttribute("user");     //注销session中的username对象
        session.removeAttribute("perms");
        //session.invalidate();                 //关闭session

        jResult.setCode(
                StatusCode.OK.getValue()).
                setContent(null).
                setMsg("注销成功");
        return jResult;
    }

}

