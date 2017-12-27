/* 
 * Copyright (C) 2014-2015 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: JedisUtil.java 
 *
 * Created: [2015年5月28日 下午4:19:47] by jie 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: ephwealth-core 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.huizhongcf.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/** 
 *
 * Description: Jedis 工具类
 *
 * @author lijie 
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2015年5月28日      lijie       1.0         1.0 Version 
 * </pre>
 */

public class JedisUtil {
	
	private static final Log log = LogFactory.getLog(JedisUtil.class);
	
	private static ShardedJedisPool shardedJedisPool = (ShardedJedisPool) Environment.getInstance().getBean("shardedJedisPool");
	
	public static String set(String key, String value){
		ShardedJedis jedis = shardedJedisPool.getResource();
        String result = null;
		try {
			result = jedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static String get(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		String result = null;
		try {
			result = jedis.get(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static Long del(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = null;
		try {
			result = jedis.del(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static Long incr(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = null;
		try {
			result = jedis.incr(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static Long expire(String key, int seconds){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = null;
		try {
			result = jedis.expire(key, seconds);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static Boolean exists(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Boolean result = false;
		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static String hmset(String key, Map<String, String> map){
		ShardedJedis jedis = shardedJedisPool.getResource();
		String result = null;
		try {
			result = jedis.hmset(key, map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	public static List<String> hmget(String key, String... vkey){
		ShardedJedis jedis = shardedJedisPool.getResource();
		List<String> result = null;
		try {
			result = jedis.hmget(key, vkey);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
	}
	
	/**
	 * 
	 * Description: 分页查询缓存中的数据
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @Author chengzhichao
	 * @Create Date: 2015年8月4日 下午10:36:49
	 */
	public static Set<String> zrevrange(String key, int start, int end){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Set<String> result = null;
		try {
			result = jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * Description: 统计总数
	 *
	 * @param key
	 * @return
	 * @Author chengzhichao
	 * @Create Date: 2015年8月5日 下午5:33:10
	 */
	public static Long zcard(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = null;
		try {
			result = jedis.zcard(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 
	 * Description: 缓存里面添加数据
	 *
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 * @Author chengzhichao
	 * @Create Date: 2015年8月5日 下午5:37:42
	 */
	public static Long zadd(String key, Long score, String member){
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = null;
		try {
			result = jedis.zadd(key, score, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	
}
