package com.wonders.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */

public class PreZuulFilter extends ZuulFilter {
    protected static final Logger LOGGER = LoggerFactory.getLogger(PreZuulFilter.class);

    /**
     * filterType() - 按类型对过滤器进行分类。Zuul中的标准类型是：
     * pre 在请求到达origin之前执行，在这一步可以做认证，选择转发地址，记录日志
     * Routing 建立http请求，可以使用httpClient或者netflix的ribbon
     * Post 返回请求，可以在这一步做统计收集，设置response的http heder，把请求返回个客户端
     * Error当在以上三个步骤执行出错时执行
     * static支持静态响应的“static”类型过滤器
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序，数字越大优先级别越低
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    /**
     * 返回 true 时run方法将执行，否则不执行
     */
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        //获取客户端IP地址
        String remoteHost = request.getRemoteHost();
        PreZuulFilter.LOGGER.info(String.format("remoteHost:%s", remoteHost));
        return null;
    }
}
