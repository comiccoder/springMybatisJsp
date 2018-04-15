package example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//@EnableWebMvc---会改变整体的设计
@Configuration   //标识这是一个配置文件
public class mySpringMVCConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public  MyInterceptor myInterceptor()
    {   return  new MyInterceptor();    }


    //拦截器对哪些链接有效
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(myInterceptor()).excludePathPatterns("/static/*")
                .excludePathPatterns("/error").addPathPatterns("/**").excludePathPatterns("/user/login");
    }
}
