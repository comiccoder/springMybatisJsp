package example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import example.entity.JResult;
import example.entity.StatusCode;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class MyInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {
        String requestUri = request.getRequestURI(); //请求完整路径，可用于登陆后跳转
        String contextPath = request.getContextPath();  //项目下完整路径
        String url = requestUri.substring(contextPath.length()); //请求页面，以后可以在登陆后再打开

        Object obj = request.getSession().getAttribute("user");

        System.out.println("自定义拦截器。。。。");
        //如果没有登陆
        if(null==obj)
        {
            System.out.println("用户未登陆");
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求响应头会有，x-requested-with
                System.out.print("发生ajax请求...");
                //返回正常的权限
                JResult result = new JResult();
                result.setCode(StatusCode.FAIL.getValue()).setContent(null).setMsg("请登陆访问");

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");

                PrintWriter out=null;
                out = response.getWriter();
                String out_str=JSON.toJSONString(result);
                out.append(out_str);

                return false;  //拦住之后，在页面提示，让用户自己去登陆
            }else
            {
                //System.out.println("当前请求的url是:"+url);
                //如果请求的是admin相关的路径，就转到到admin的登录页面 匹配  /admin**这样的路径
                String regex = "^/{1}(?:admin){1}[\\w-_/?&=#%:]*$";
                if(url.matches(regex)){
                    request.getRequestDispatcher("/noAuth/adminLogin").forward(request,response);
                    return false;  //当返回true，是继续执行之后请求链；返回false 就停止当前请求了
                }else
                {
                    request.getRequestDispatcher("/noAuth/login").forward(request,response);
                    return false;  //当返回true，是继续执行之后请求链；返回false 就停止当前请求了
                }
            }
        }else
        {
            System.out.println("用户已登陆");
            //查看当前的用户是否有相对的权限// 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 从方法处理器中获取出要调用的方法
            Method method = handlerMethod.getMethod();
            // 获取出方法上的Access注解
            Access access = method.getAnnotation(Access.class);

            if (access == null) {
                // 如果注解为null, 说明不需要拦截, 直接放过
                return true;
            }

            if (access.authorities().length > 0)  //当前请求有没有权限要求
            {
                // 如果权限配置不为空, 则取出配置值
                String[] authorities = access.authorities();
                Set<String> authSet = new HashSet<>();
                for (String authority : authorities) {
                    // 将权限加入一个set集合中
                    authSet.add(authority);
                }

                // 这里我为了方便是直接参数传入权限, 在实际操作中应该是从参数中获取用户Id
                // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验
                //这里初始化了一个用户的权限，正确的方式应该是到数据库中查找


                Set<String> perms_session = new HashSet<String>();
                perms_session =(HashSet<String>) request.getSession().getAttribute("perms");

                boolean isOk=true;  //是否有权限不存在
                for (String authority : authSet)
                {
                    //System.out.println(authority);
                    if(!perms_session.contains(authority))
                    {
                        isOk=false;
                        break;
                    }
                }

                //当前没有权限
                if(!isOk)
                {
                    if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                        //如果是ajax请求响应头会有，x-requested-with
                        System.out.print("发生ajax请求...");
                        //返回正常的权限
                        JResult result = new JResult();
                        result.setCode(StatusCode.FAIL.getValue()).setContent(null).setMsg("没有该页面权限");

                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=utf-8");

                        PrintWriter out=null;
                        out = response.getWriter();
                        String out_str=JSON.toJSONString(result);
                        out.append(out_str);

                        return false;  //拦住之后，在页面提示，让用户自己去登陆
                    }else
                    {
                        //强制转到login页面
                        response.sendRedirect("noAuth");
                        return false;  //当返回true，是继续执行之后请求链；返回false 就停止当前请求了
                    }
                }
            }
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
