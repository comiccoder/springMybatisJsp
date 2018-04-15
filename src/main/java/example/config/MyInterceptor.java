package example.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class MyInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {
        Object obj = request.getSession().getAttribute("user");

        System.out.println("自定义拦截器。。。。");
        if(null == obj)
        {
            System.out.println("用户未登陆");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.append("你还没有登陆，请登陆去");

            response.sendRedirect("/login");  //直接跳转到login页面

            return true;  //当返回true，是继续执行之后请求链；返回false 就停止当前请求了
        }else
        {
            System.out.println("用户未登陆");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
