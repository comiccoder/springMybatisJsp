package example.web;

import example.entity.Permission;
import example.entity.User;
import example.entity.UserVo;
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

@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

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

    @RequestMapping("/findUser")
    public ModelAndView page2(Model model, @RequestParam("userId") String userId)
    {
        //将字符串转成数字类型
        int id= Integer.parseInt(userId);

        ModelMap modelMap = new ModelMap();
        User  returnUser = userService.findUser(id);
        //要返回的数据
        modelMap.addAttribute("user",returnUser);
        return new ModelAndView("findUser", modelMap);
    }


     /******************************************
     * 添加用户相关信息
     ******************************************/
    @RequestMapping(value="/openAddUser",method = RequestMethod.GET)
    public String openAddUser()
    {
        return "addUser";
    }

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String adddUser(User user)
    {
        try {
            userService.addUser(user);
        }catch(Exception e)
        {

            return "error";
        }
        //获取用户的信息
        //return  "userList";
        return "redirect:/user/userList";
    }

    /******************************************
        //按照分页来列出用户信息
        //page是指当当前选择看的是第几页，默认是空
    *****************************************/
    @RequestMapping("/listUserPage")
    public ModelAndView listUserPage(String page,Model model)
    {
        int pageSize=10;  //每页显示10条，一般这个设置放在属性文件中
        List<UserVo> users = new ArrayList<UserVo>();

        int count;  //记录总数
        count = userService.getCount();

        //获取出符合条件的用户到底有都少条
        if(null == page) {  //如果为空
                page = "1";
        }

        //总页数
        int pageTimes;
        if(count%pageSize == 0)
        {
            pageTimes = count/pageSize;
        }else
        {
            pageTimes = count/pageSize + 1;
        }
        model.addAttribute("pageTimes", pageTimes);

        //计算应该选择的信息，是从哪条开始，哪条结束
        int startRow = (Integer.parseInt(page)-1) * pageSize;
        users = userService.getUserVoByPage(startRow, pageSize);

        ModelMap modelMap = new ModelMap();

        //要返回的数据
        modelMap.addAttribute("users",users);
        model.addAttribute("userNum",count); //总条数
        model.addAttribute("currentPage", Integer.parseInt(page));

        return new ModelAndView("userListPage", modelMap);
    }


    /******************************************
     ////***login相关
     *****************************************/
    @RequestMapping(value="login",method = RequestMethod.GET)
    public String login()
    {
        return "login/login";
    }

    //发起请求
    @RequestMapping(value="login",method = RequestMethod.POST )
    public String login(       User user,
                               HttpSession session,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
        User myUser = null;
        List<Permission> perms=new ArrayList<Permission>();
        Set<Permission> perms_set=new HashSet<Permission>();

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
                return "redirect:../hellow";
            }else
            {
                //将表示失败的数据写回到页面上
                request.setAttribute("msg","登录失败");
                request.setAttribute("ok","2"); //0表示失败
                //request.getRequestDispatcher("login/login");
                //return "redirect:login";
                return "login/login";
            }
        }else  return "login";
        //return "login";
    }
}

