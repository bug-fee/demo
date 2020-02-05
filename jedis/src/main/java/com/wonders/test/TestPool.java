package com.wonders.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPool {
	public static void main(String[] args) {
		JedisPool jedisPool=JedisPoolUtil.getJedisInstance();
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			jedis.set("test","123");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//获取实例失败时释放jedis实例
			JedisPoolUtil.release(jedisPool, jedis);
		}
	}

}
