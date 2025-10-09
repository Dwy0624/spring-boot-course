package top.dwy.boot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.dwy.boot.filter_interceptor.filter.CorsFilter;
import top.dwy.boot.filter_interceptor.filter.LogFilter;
import top.dwy.boot.filter_interceptor.filter.RateLimitFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alani
 */
@Configuration
@AllArgsConstructor
public class FilterConfig {
    private final LogFilter logFilter;
    private final RateLimitFilter rateLimitFilter;
    private final CorsFilter corsFilter;

    // 1. 跨域过滤器（order=0，最高优先级）
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter);
        registration.setUrlPatterns(List.of("/*"));
        registration.setOrder(0);
        return registration;
    }

    // 2. 日志过滤器（order=2）
    @Bean
    public FilterRegistrationBean<LogFilter> logFilterRegistration() {
        FilterRegistrationBean<LogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(logFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registration.setUrlPatterns(urlPatterns);
        registration.setOrder(2);
        registration.addInitParameter("exclusions", "*.js,*.css,*.png");
        return registration;
    }

    // 3. 限流过滤器（order=3）
    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterRegistration() {
        FilterRegistrationBean<RateLimitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(rateLimitFilter);
        registration.addUrlPatterns("/api/pay/*");
        registration.setOrder(3);
        return registration;
    }


    /*
    private final MyFilter myFilter;
    private final YourFilter yourFilter;
    private final RateLimitFilter rateLimitFilter;

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterFilterRegistrationBean(){
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rateLimitFilter);
        registrationBean.addUrlPatterns("/api/pay/*","/api/order/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean(){
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/test");
        registrationBean.setOrder(2);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<YourFilter> yourFilterFilterRegistrationBean(){
        FilterRegistrationBean<YourFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new YourFilter());
        registrationBean.addUrlPatterns("/test");
        registrationBean.setOrder(1);
        return registrationBean;
    }
    */
}
