package example.web;

import example.config.Access;
import example.entity.*;
import example.service.PromotionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/****************
 * // 活动的相关控制
 ***************/


@Controller
public class AdminPromotionController
{
    @Autowired
    private PromotionService promotionService;

    /******************************************
     //按照分页来列出用户信息
     //page是指当当前选择看的是第几页，默认是空
     *****************************************/
    @RequestMapping("admin/listAdminPromotionPage")
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
        modelMap.addAttribute("user",obj);      //用户信息

        // --- 权限活动情况
        return new ModelAndView("admin/listAdminPromotionPage", modelMap);
    }

    //---管理员的添加权限
    @Access(authorities = {"admin/add"})
    @RequestMapping(value="admin/openAddPromotion",method = RequestMethod.GET)
    public String openAddPromotion()
    {
        return "admin/addPromotion";
    }


    @Access(authorities = {"admin/add"})
    @ResponseBody
    @RequestMapping(value="admin/addPromotion",method = RequestMethod.POST)
    public JResult addPromotion(
            Promotion promotion,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response)
    {

        JResult jResult=new JResult();  //返回
        //try{

        Format format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateStr  = format1.format(new Date()); 	//格式化系统时间
        promotion.setUpdateTime(updateStr);

        promotionService.addPromotion(promotion);
            jResult.setCode(
                    StatusCode.OK.getValue()).
                    setContent(null).
                    setMsg("添加活动成功");

//        }catch (Exception e)
//        {
//            jResult.setCode(
//                    StatusCode.failCode.getValue()).
//                    setContent(null).
//                    setMsg("添加活动失败");
//
//        }
        return jResult;
    }

    @Access(authorities = {"admin/edit"})
    @ResponseBody
    @RequestMapping(value="admin/deletePromotionById",method = RequestMethod.POST)
    public JResult deletePromotion(
            @RequestParam("promotionId") String promotionId,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response)
    {
        JResult jResult=new JResult();
        int int_id=Integer.parseInt(promotionId);

        promotionService.deletePromotionById(int_id);

        jResult.setCode(
                StatusCode.OK.getValue()).
                setContent(null).
                setMsg("删除活动成功");

        return jResult;
    }


    //---管理员的添加权限
    @Access(authorities = {"admin/edit"})
    @RequestMapping(value="admin/openEditPromotion",method = RequestMethod.GET)
    public ModelAndView openEditPromotion(
            @RequestParam("promotionId") String promotionId
    )
    {
        Promotion promotion = new Promotion();
        promotion =promotionService.getPromotionById(Integer.parseInt(promotionId));
        ModelMap modelMap = new ModelMap();
        //要返回的数据
        modelMap.addAttribute("promotion",promotion);

        // --- 权限活动情况
        return new ModelAndView("admin/editPromotion", modelMap);
    }


    @Access(authorities = {"admin/edit"})
    @ResponseBody
    @RequestMapping(value="admin/editPromotion",method = RequestMethod.POST)
    public JResult editPromotion(
            Promotion promotion,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response)
    {
        JResult jResult=new JResult();  //返回

        //try{
        Format format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateStr  = format1.format(new Date()); 	//格式化系统时间
        promotion.setUpdateTime(updateStr);

        promotionService.updatePromotion(promotion);
        jResult.setCode(
                StatusCode.OK.getValue()).
                setContent(null).
                setMsg("更新活动成功");

//        }catch (Exception e)
//        {
//            jResult.setCode(
//                    StatusCode.failCode.getValue()).
//                    setContent(null).
//                    setMsg("添加活动失败");
//
//        }
        return jResult;
    }
}
