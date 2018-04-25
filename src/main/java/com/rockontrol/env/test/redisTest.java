package com.rockontrol.env.test;

import com.rockontrol.env.utils.RedisUtils;

import redis.clients.jedis.Jedis;


public class redisTest {
	public static void main(String[] args) {
		Jedis redis = RedisUtils.getRedis();
		try {
			String updatetime = redis.get("checkpoint.update.time");
			System.out.println(updatetime);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			RedisUtils.close(redis);
		}	
	}

}
