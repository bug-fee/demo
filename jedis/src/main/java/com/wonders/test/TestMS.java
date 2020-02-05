package com.wonders.test;

import redis.clients.jedis.Jedis;

/**
 * 测试主从复制
 * @author Wang sirui
 * @version 1.0 2018年3月24日
 * @author 下午10:50:28 Administrator
 * @since 1.0
 */
public class TestMS {

	/**
	 * 不建议在api中配置主从，会出现bug或主库写完数据，未来得及同步到从库，导致从库读取不到数据
	 **/
	public static void main(String[] args) {
		Jedis jedis_M = new Jedis("192.168.43.49", 6379);
		Jedis jedis_S = new Jedis("192.168.43.49", 6380);
		jedis_S.slaveof("192.168.43.49", 6379);
		
		jedis_M.set("class", "112211");
		System.out.println(jedis_S.get("class"));
		
	}
}
