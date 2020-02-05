package com.wonders;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 2018-10-20
 * @Rewrite record：
 * 1、
 */
@SpringBootApplication
//支持zuul代理
@EnableZuulProxy
public class ZuulFallBackApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(ZuulFallBackApplicationConfiguraton.class, args);
    }

    /**
     * 支持使用 http://localhost:7906/hystrix.stream
     *
     * @author Wang si rui
     * @version 1.0
     */
    @Bean
    public ServletRegistrationBean getServlet() {

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        //系统启动时加载顺序
        registrationBean.setLoadOnStartup(1);
        //设置hystrix 输出流路径，通过http://localhost:7906/hystrix 进入控制面板路径录入输出流路径可在面板查看hystrix状态
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

