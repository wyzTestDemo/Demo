package com.example.yyb.config;

import com.example.yyb.controller.filter.AdminFilter;
import com.example.yyb.controller.filter.CharEncodingFilter;
import com.example.yyb.controller.filter.IndexFilter;
import com.example.yyb.controller.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

@Configuration
public class WebConfig {
    @Bean
    public Filter indexFilter() {
        return new IndexFilter();
    }

    @Bean
    public Filter adminFilter() {
        return new AdminFilter();
    }

    @Bean
    public Filter charEncodingFilter() {
        return new CharEncodingFilter();
    }

    @Bean
    public Filter userFilter() {
        return new UserFilter();
    }

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        String[] urlPatterns = {"/index"};
        FilterRegistrationBean registration = createBeanFilter("indexFilter", urlPatterns);
        return registration;
    }
    @Bean
    public FilterRegistrationBean adminFilterRegistration() {
        String[] urlPatterns = {"/admin/*"};
        FilterRegistrationBean registration = createBeanFilter("adminFilter", urlPatterns);
        return registration;
    }
    @Bean
    public FilterRegistrationBean charEncodingFilterRegistration() {
        String[] urlPatterns = {"/*"};
        FilterRegistrationBean registration = createBeanFilter("charEncodingFilter", urlPatterns);
        registration.setOrder(1);
        return registration;
    }
    @Bean
    public FilterRegistrationBean userFilterRegistration() {
        String[] urlPatterns = {"/myPosting","/myupload/*","/myUser/*","/upload","/cancelCollection","/collectionMusic"};
        FilterRegistrationBean registration = createBeanFilter("userFilter", urlPatterns);
        return registration;
    }

    public FilterRegistrationBean createBeanFilter(String targetBeanName, String[] urlPatterns) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy(targetBeanName));
        registration.addUrlPatterns(urlPatterns);
        registration.setName(targetBeanName);
        /*registration.setOrder(1);setOrder(1)方法中参数的值越小，优先级越高*/
        return registration;
    }
}
