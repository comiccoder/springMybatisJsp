package example.web;

import example.entity.Promotion;
import example.entity.PromotionVo;
import example.entity.User;
import example.entity.UserVo;
import example.service.PermissionService;
import example.service.PromotionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/****************
 * // 活动的相关控制
 ***************/


@Controller
public class PromotionController
{
    @Autowired
    private PromotionService promotionService;

    /******************************************
     //按照分页来列出用户信息
     //page是指当当前选择看的是第几页，默认是空
     *****************************************/
    @RequestMapping("noAuth/listPromotionPage")
    public ModelAndView listPromotionPage(
            String page,
            Model model,
            HttpServletRequest request
    )
    {
        ModelMap modelMap = new ModelMap();

        int pageSize=10;  //每页显示10条，一般这个设置放在属性文件中
        List<PromotionVo> promotionVos = new ArrayList<PromotionVo>();

        int count;  //记录总数条数
        count = promotionService.getCount();

        //根据userId来分页的

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

        int session_user_id=-1;
        //获取session，然后判断是否登陆，来输入userId，如果没有，就设置成-1
        Object obj =null;
        obj = request.getSession().getAttribute("user");
        if( null !=obj)
        {
            session_user_id=((User)obj).getId();
        }
        promotionVos = promotionService.getPromotionVoByPage(startRow, pageSize,session_user_id);

        //要返回的数据
        modelMap.addAttribute("promotionVos",promotionVos);
        modelMap.addAttribute("promotionNum",count); //总条数
        modelMap.addAttribute("currentPage", Integer.parseInt(page));
        //modelMap.addAttribute("user",obj);      //用户信息

        return new ModelAndView("noAuth/listPromotionPage", modelMap);
    }


    /*************************************
    *做一个申请，如果成功就，提示成功，并将按钮修改成成功
    *************************************/
    @RequestMapping("promotion/applyPromotion")
    public ModelAndView applyPromotion(String page, Model model)
    {

        return null; //这样就方便了
    }

}
