package com.wonders.client;

import com.wonders.config.FeignConfiguration2;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181030
 * @Rewrite record：
 * 1、
 */
//没有填写url时name作为服务的VIP访问地址;填写URL时name作为服务的唯一名称,URL作为访问地址。
//为FeignClient创建子容器，配置信息由FeignCOnfiguration2管理，该配置类定义了拦截器用于Eureka访问时需要的账户密码。
@FeignClient(name = "xxxx", url = "http://localhost:8761/", configuration = FeignConfiguration2.class)
public interface FeignClient2 {

    //Feign接受参数必须使用@Param不能使用@PathParam
    @RequestLine(value="GET /eureka/apps/{serviceName}")
    public String findServiceInfoFromEurekaByServiceName(@Param("serviceName") String serviceName);

    @RequestLine("GET /actuator/health")
    public String health();

}
