package com.wonders.test;

import redis.clients.jedis.Jedis;

public class TestPing {

	public static void main(String[] args) {
		Jedis jedis=new Jedis("192.168.43.49",6379);
		System.out.println(jedis.ping());
	}

}
