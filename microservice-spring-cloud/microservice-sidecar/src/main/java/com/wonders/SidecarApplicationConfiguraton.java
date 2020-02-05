package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

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
/**
 *  1.sidecar 支持代理异构服务（其他编程语言建立的微服务），需要该服务提供健康监控节点/health.json
 *  并且该节点返回类似格式的JSON字符串 {status:"UP"}，sidecar实际上是将异构服务上的健康状态写入到自己的监控状态上，如果异构服务为DOWN时sidecar的health也将为DOWN
 *  可以通过/sidecar/** 或/servicename/** 访问被代理服务内的所有请求
 *  2.通过/访问sidecar的首页
 * @author Wang si rui
 * @version 1.0
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 				1、 <br>
 */
@SpringBootApplication
//支持zuul代理
@EnableSidecar
public class SidecarApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(SidecarApplicationConfiguraton.class,args);
    }

}

