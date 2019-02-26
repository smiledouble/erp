package com.cxs.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.cxs.sys.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/19 14:05
 */
@Configuration
@ConfigurationProperties(prefix="shiro.web")
public class ShiroConfig {
    private String[] ingoreUrls;
    private String[] logOuts;
    private String[] authUrls;
    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;

    public String[] getIngoreUrls() {
        return ingoreUrls;
    }

    public void setIngoreUrls(String[] ingoreUrls) {
        this.ingoreUrls = ingoreUrls;
    }

    public String[] getLogOuts() {
        return logOuts;
    }

    public void setLogOuts(String[] logOuts) {
        this.logOuts = logOuts;
    }

    public String[] getAuthUrls() {
        return authUrls;
    }

    public void setAuthUrls(String[] authUrls) {
        this.authUrls = authUrls;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    /**
     * 创建凭证匹配器
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "shiro")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        return new HashedCredentialsMatcher();
    }


    @Bean
    public SecurityManager getSecurityManager(MyRealm myRealm, HashedCredentialsMatcher hashedCredentialsMatcher) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;

    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        for (String url : ingoreUrls) {
            map.put(url, "anon");
        }
        for (String url : logOuts) {
            map.put(url, "logout");
        }
        for (String url : authUrls) {
            map.put(url, "authc");
        }
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 登陆成功的页面 可以不配置，在controller里面去跳转
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        // 错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 注册shiro的委托过滤器，相当于之前在web.xml里面配置的
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    //这里是为了能在html页面引用shiro标签，上面两个函数必须添加，不然会报错
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
