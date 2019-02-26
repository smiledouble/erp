package com.cxs.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/18 16:06
 */
@Configuration
@Getter
@Setter
@ConditionalOnClass({DruidDataSource.class})
@ConfigurationProperties(prefix = "spring.druid.web")
public class DruidConfig {

    private String loginUsername;
    private String loginPassword;
    private String allow;
    private String deny;
    private String druidRegistrationUrl;

    private String exclusions;
    private String filtersUrlPatterns;


    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource getDruidDataSources() {
        return new DruidDataSource();
    }


    /**
     * 配置监控页面  注册StatViewServlet
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> getStatViewServlet() {
        //创建Servlet的对象
        StatViewServlet servlet = new StatViewServlet();
        //创建注册器
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>();
        //注册
        registrationBean.setServlet(servlet);
        //设置其它参数
        registrationBean.addInitParameter("loginUsername", loginUsername);
        registrationBean.addInitParameter("loginPassword", loginPassword);
        registrationBean.addInitParameter("allow", allow);
        registrationBean.addInitParameter("deny", deny);
        registrationBean.addUrlMappings(druidRegistrationUrl);
        registrationBean.setLoadOnStartup(-1);
        return registrationBean;

    }

    /**
     * 注册过滤器WebStatFilter
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> getWebStatFilter() {
        WebStatFilter statFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(statFilter);
        registrationBean.addInitParameter("exclusions", exclusions);
        Collection<String> urlPatterns = new ArrayList<>();
        urlPatterns.add(filtersUrlPatterns);
        registrationBean.setUrlPatterns(urlPatterns);

        return registrationBean;

    }

}
