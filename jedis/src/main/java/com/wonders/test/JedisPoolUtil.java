package com.wonders.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = null;

	private JedisPoolUtil() {

	}

	/**
	 * 获取连接池实例
	 * 创建人：王思瑞<br>
	 * 创建日期：2018年3月25日 下午9:11:14<br>
	 */
	public static JedisPool getJedisInstance() {
		if (jedisPool == null) {
			synchronized (JedisPool.class) {
				if (jedisPool == null) {
					JedisPoolConfig poolConfig=new JedisPoolConfig();
					poolConfig.setMaxActive(1000);//设置最大连接池数量
					poolConfig.setMaxIdle(32);//设置最大空闲量
					poolConfig.setMaxWait(100*1000);//设置获取jedis实例时最大等待时间
					poolConfig.setTestOnBorrow(true);//确保获取的每个实例都是可用的
					jedisPool=new JedisPool(poolConfig, "192.168.43.49",6379);
				}
			}
		}
		return jedisPool;
	}
	
	/**
	 * 归还jedis实例
	 * 创建人：王思瑞<br>
	 * 创建日期：2018年3月25日 下午9:11:31<br>
	 */
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
