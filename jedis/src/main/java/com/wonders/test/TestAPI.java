package com.wonders.test;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestAPI {
	Jedis jedis;

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.43.49", 6379);
		System.out.println(jedis.ping());
	}

	@Before
	public void beforeTest() {
		jedis = new Jedis("192.168.43.49", 6379);
	}

	@Test
	public void testSet() {
		///jedis.set("k1","v1");
		//jedis.set("k2","v2");
		//jedis.set("k3","v3");
		//System.out.println(jedis.get("k1"));//获取k1的值
		//System.out.println(jedis.keys("*"));//查询所有key
		//System.out.println(jedis.hget("user","id"));
		//jedis.hset("user", "id","11");//装入hash
		//System.out.println(jedis.hget("user","id"));//获取user的id属性值
		//jedis.hset("user", "name", "tom");//在user中设置name属性值
		//System.out.println(jedis.hgetAll("user"));//获取名为user的hash所有键值
		//System.out.println(jedis.setex("k4", 12, "v4"));//设置key并且设置有效期为12秒(jedis操作成功是返回字符串"OK")
		//System.out.println(jedis.get("k4"));
		jedis.expire("user", 1);
		System.out.println(jedis.keys("*"));
	}
	
	/**
	 * 描述：测试事务<br>
	 */
	@Test
	public void testTransaction(){
		Transaction transaction = jedis.multi();
		transaction.set("k4","k44");
		transaction.set("k5","k55");
		//transaction.exec();//执行事务
		//transaction.discard();//放弃执行事务,抛异常是放弃执行
	}
	/**
	 * 模拟刷信用卡
	 * @throws InterruptedException 
	 **/
	@Test
	public void testLock() throws InterruptedException{
		int atmToSubtract=10;
		int balance;
		int debt;
		balance=Integer.parseInt(jedis.get("balance"));
		if(balance<atmToSubtract){
			System.out.println("可刷的信用卡额度不够");
		}
		jedis.watch("balance");
		Thread.sleep(7000L);//模拟信用卡在别处被刷
		Transaction transaction = jedis.multi();
		transaction.incrBy("debt",atmToSubtract);//设置欠款额加上刚刚刷的钱数。
		transaction.decrBy("balance",atmToSubtract);//可刷额度减少10。
		transaction.exec();//执行事务
		balance=Integer.parseInt(jedis.get("balance"));
		debt=Integer.parseInt(jedis.get("debt"));
		System.out.println("balance:"+balance);
		System.out.println("debt:"+debt);
		jedis.unwatch();//关闭监控
	}
}
