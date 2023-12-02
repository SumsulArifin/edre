package com.tkgroupbd.pusti.api.configs.ipandbrowserconfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestInfoFilter> loggingFilter() {
        FilterRegistrationBean<RequestInfoFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestInfoFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
