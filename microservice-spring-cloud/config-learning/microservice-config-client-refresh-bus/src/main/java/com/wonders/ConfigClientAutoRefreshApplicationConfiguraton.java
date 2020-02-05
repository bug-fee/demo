package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 2018-10-20
 * @Rewrite record：
 * 1、
 * <p>
 * config-client 启动顺序：
 * 先加载bootstrap.*里面的配置，加载完之后链接config server 加载远程配置信息，最后加载application.*里面的配置
 */

/**
 * config-client 启动顺序：
 * 先加载bootstrap.*里面的配置，加载完之后链接config server 加载远程配置信息，最后加载application.*里面的配置
 */
//访问(必须手动访问)http://127.0.0.1:8081/bus/refresh 可实现半自动刷新(需配置rabbitmq支持,实现事件传播):所有config client将全部刷新配置信息。(必须为POST请求,可用cmd执行 “curl -X POST  http://127.0.0.1:8081/bus/refresh” 模拟实现)
//如果需要实现全自动刷新 ，则需要在github上开启webhook(钩子)功能(提交代码后自动发起POST请求)，可配合ngrok使用。
/**
 * 1.实现部分应用刷新配置(滚动刷新，想要先实现一部分，后续实现其余部分刷新)只需要访问 /bus/refresh?destination=cusomers:9000 即可，customers表示应用名称。
 * 2.访问 /bus/refresh?destination=cusomers:** 可实现指定应用名称的服务(无论端口)全部刷新配置
 * 3.此处有坑，如果不同主机发布应用名称相同且端口也相同的集群时，此时刷新则属于该应用名称的服务将只会被刷新一个，其他实例将被过滤，想要全部刷新可配置 spring.application.index='${random.long}'
 * 4.访问 /trace 端点可报告所有Web请求的详细信息，包括请求方法、路径、时间戳以及请求和响应的头信息，记录每一次请求的详细信息(也可利用此端点查看 调用 /bus/refresh 后具体刷新了哪些应用的config 信息  destination: "**:**" 表示刷新了所有应用的所有端口集群)
 * 5.将/bus/refresh端点 集成到config-client 这种做法不太优雅，也可以将spring-cloud-starter-bus-amqp依赖加入到config-server中，并配置rabbitmq地址，同样可以实现访问 /bus/refresh 刷新所有config-client应用。
 * 6.实现config-server的高可用。方式一：可使用nginx负载均衡多个cofig-server的地址(config-server未集成到eureka),第二种方式：是多个config-server集成到eureka上去，当config-client访问config-server时从eureka获取服务地址自动负载均衡。
 */
@SpringBootApplication
public class ConfigClientAutoRefreshApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientAutoRefreshApplicationConfiguraton.class, args);
    }


}

