package example.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access
{
    String[] authorities() default {};  //使用的权限
//  String[] roles() default {};
//  String[] value() default {};
}

