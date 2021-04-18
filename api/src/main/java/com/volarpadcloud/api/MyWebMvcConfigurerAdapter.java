package com.volarpadcloud.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 等同配置文件#spring.mvc.static-path-pattern=/static/**
 * 但可以放行其他地址，配置文件中我不知道怎么放行...
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {
//    @Bean
//    public HandlerInterceptor getAccessInterceptor() {
//        return new AccessInterceptor();
//    }

    @Bean
    public InternalResourceViewResolver resourceViewResolver() {
        //System.out.println("[JSP_REG]");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //请求视图文件的前缀地址
        resolver.setPrefix("/jsp/");
        //请求视图文件的后缀
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        /*放行swagger*/
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

//    public void addInterceptors(InterceptorRegistry registry) {
//        //addPathPatterns 用于添加拦截规则
//        //excludePathPatterns 用于排除拦截
//        registry.addInterceptor(getAccessInterceptor()).addPathPatterns("/**")
//                /*放行swagger*/
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//              /*  .excludePathPatterns("/hlladmin/login") //登录页
//                .excludePathPatterns("/hlladmin/user/sendEmail") //发送邮箱
//                .excludePathPatterns("/hlladmin/user/register") //用户注册
//                .excludePathPatterns("/hlladmin/user/login"); //用户登录*/
//        super.addInterceptors(registry);
//    }

}
