package com.wonders.client;

/**
 * 定义一个实现了Client的接口用于给FallbackFactory处理具体回调
 *
 * @author Wang si rui
 * @version 1.0
 * @return 返回值
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 1、 <br>
 * @see HystrixClientFactory#create(java.lang.Throwable)
 */
public interface UserFeignClientWithFactory extends UserFeignClient {
}
