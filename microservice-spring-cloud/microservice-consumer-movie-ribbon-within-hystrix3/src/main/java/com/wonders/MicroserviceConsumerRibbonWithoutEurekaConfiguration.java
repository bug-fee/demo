package com.wonders;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * (1)在微服务中,单个服务为了高可用性防止单点故障通常会集群部署。由于网路或其他原因服务变得不可用时服务调用者会出现长等待的线程阻塞,此时会有大量的其他请求涌入,servlet容器线程资源会被消耗完毕。服务之间有依赖性,于是会出现故障传播引起雪崩效应,断路器就是为了解决这样的问题。
 * <p>
 * (2)在服务与用户之间通过api进行交互,用户调用api消费服务,当某个服务不可用(有一个时间阈值)时断路器就会打开,同时为这个服务的调用者返回一个固定的值。简单来说就是为服务的瘫痪做一个保险,防止用户得不到服务返回结果而阻塞等待进而影响其他服务和用户。
 * <p>
 * (3)当hystrix检测到服务不可用时,会自动断开不可用服务,具体的就是每次消费者调用方法都不再去访问服务提供者,而是直接返回一个设定好的固定值。当服务再次可用时在打开服务,同时允许消费者调用方法再去访问服务提供者。如果hystrix打开服务如何知道服务可用呢？hystrix允许额外发送一些请求到服务提供者检测服务是否可用。
 *
 * @author Wang si rui
 * @version 1.0
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 1、 <br>
 */

@SpringBootApplication
@EnableEurekaClient
//支持断路器
@EnableCircuitBreaker
//支持断路器控制面板http://localhost:7906/hystrix
@EnableHystrixDashboard
public class MicroserviceConsumerRibbonWithoutEurekaConfiguration {

    public static void main(String[] args) {
        //日期类型格式化显示为yyyyMMdd
        //Date now = new Date();
        //System.out.println(String.format("%tC%ty%tm%td", now, now, now, now));
//        System.out.printf("日期类型格式化显示为yyyyMMdd：%tC%ty%tm%td",now);

        //启动springboot 容器
        SpringApplication.run(MicroserviceConsumerRibbonWithoutEurekaConfiguration.class, args);

    }

    @Bean
    //添加LoadBalanced就能让RestTemplate在请求时拥有客户端负载均衡能力
    @LoadBalanced
    //初始化bean：方法名称代表注入的bean的id;如果是按类型匹配则bean的ID可以与方法名称不一致（测试可行）
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 使用 http://localhost:7906/hystrix.stream
     * @author Wang si rui
     * @version 1.0
     */
    @Bean
    public ServletRegistrationBean getServlet(){

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
