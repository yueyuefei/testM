package com.rockontrol.env.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis/Redis 客户端
 * @author lvshengchao 2015-6-12 下午3:19:08
 *
 */
public class RedisUtils {
	static JedisPool jedispool = null;

	/**
	 * 获取redis连接池
	 * @return
	 *
	 * @author lvshengchao 2015-6-12 下午3:15:19
	 */
	static JedisPool get_jedispool() {
		if (jedispool == null) {
			synchronized (RedisUtils.class) {
				if(jedispool == null){
					JedisPoolConfig jedispool_config = new JedisPoolConfig();
					jedispool_config.setMaxTotal(300);
					jedispool_config.setMaxIdle(20);
					jedispool_config.setMaxWaitMillis(1000);
					jedispool_config.setTestOnBorrow(true);
					
					jedispool = new JedisPool(jedispool_config,"192.168.200.89",6379);
				}
			}
		}
		return jedispool;
	}
	
	/**
	 * 获取redis客户端
	 * <br />
	 * 用完之后需要调用 RedisUtils.close(Jedis)
	 * @return
	 *
	 * @author lvshengchao 2015-6-12 下午3:16:39
	 */
	public static Jedis getRedis(){
		return get_jedispool().getResource();
	}
	
	/**
	 * [关闭]redis连接
	 * <br />
	 * 后台的操作实际上是不关闭连接，而是将redis对象放回连接池
	 * @param redis
	 *
	 * @author lvshengchao 2015-6-12 下午3:17:48
	 */
	public static void close(Jedis redis){
		if(redis != null){
			get_jedispool().returnResourceObject(redis);
		}
	}
}
