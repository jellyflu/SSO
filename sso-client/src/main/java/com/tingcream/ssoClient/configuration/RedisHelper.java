package com.tingcream.ssoClient.configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

 

 
/**  
 * redis 辅助工具类
 * @author jelly
 * 
 *
 */
public class RedisHelper {
	 
	
//	/**
//	 *   key             field           value
//	 *  sessionInfos   字符串<sessionId>   <SessionInfo>对象
//	 *                  ...                ...
//	 */
//	 public static  String SESSION_INFOS="sessionInfos"; 
	 
	 
	  private RedisTemplate<Serializable, Object> redisTemplate; 
	  public void setRedisTemplate(  RedisTemplate<Serializable, Object> redisTemplate) { 
	    this.redisTemplate = redisTemplate; 
	  } 
	  public RedisTemplate<Serializable, Object> getRedisTemplate() {
		return redisTemplate;
	  }
	  
	  
	  
		  
	  
	  /**
	   * hash set
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:25
	   * @param key
	   * @param field
	   * @param value
	   */
	  public  void     hset(String key ,String field,Object value){
		    BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		    hashOperation.put(field, value);
	  }    
	  /**
	   * hash  set  with ttlSec
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:31
	   * @param key
	   * @param field
	   * @param value
	   * @param ttl
	   */
	  public   void   hset(String key ,String field,Object value,long ttl){
		    BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		    hashOperation.put(field, value);
		    redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
	  }
	  
	  
	  
	  /**
	   * hash  get
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:36
	   * @param key
	   * @param field
	   * @return
	   */
	  public  Object   hget(String key,String field){
		  BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		  return   hashOperation.get(field);
	  }
	  /**
	   * hash  hdel  field
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:41
	   * @param key
	   * @param field
	   */
	  public  void hdel(String key,Object field){
		  BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		   hashOperation.delete(field);
	  }
	  /**
	   * hash  hdel  field1  field2
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:47
	   * @param key
	   * @param fields
	   */
	  public  void hdel(final String key,final Object... fields){
		  BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		  hashOperation.delete(fields);
	  }
	  
	  
	  
	  /**
	   * hash hlen
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:52
	   * @param key
	   * @return
	   */
	  public  Long    hlen(String key){
		  BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		 return  hashOperation.size();
	  }
	  /**
	   * hash  hkeys
	   * @author jelly
	   * @date 2018年10月10日 下午5:04:57
	   * @param key
	   * @return
	   */
	  public  Set<String> hkeys(String key){
		  BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
		 return  hashOperation.keys();
	  }
	  /**
	   * hash hvals
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:02
	   * @param key
	   * @return
	   */
	  public   List<Object> hvals(String key){
		    BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
			 return  hashOperation.values();
	  }
	  /**
	   * hash  hgetall
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   * @param key
	   * @return
	   */
	  public   Map<String,Object> hgetall(String key){
		  BoundHashOperations<Serializable, String, Object> hashOperation=  redisTemplate.boundHashOps(key);
			 return  hashOperation.entries();
	  }
	  
	  
	  
	  /**
	   * 传入 多个key   
	   * key1 key2 key3
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   */
	  public void remove(final String... keys) { 
		 
	    for (String key : keys) { 
	      remove(key); 
	    } 
	  } 
	  
	  
	  /** 
	   * 批量删除key  根据匹配的parttern   
	   * 如 mylist*   能匹配 mylist1 mylist2  
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   */
	  public void removePattern(final String pattern) { 
	    Set<Serializable> keys = redisTemplate.keys(pattern); 
	    if (keys.size() > 0) {
	    	redisTemplate.delete(keys);
	    }
	       
	  } 
	  
	  /**
	   * 删除key-Object  根据传入的key
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   */
	  public void remove(final String key) { 
	      redisTemplate.delete(key); 
	  } 
	  
	  /**
	   * 判断缓存中是否存在 指定的key
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   */
	  public boolean exists(final String key) { 
	    return redisTemplate.hasKey(key); 
	  } 
	  
	  /** 
	   * 读取缓存 
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   * @param key 
	   * @return 
	   */
	  public Object get(final String key) { 
	    Object result = null; 
	    ValueOperations<Serializable, Object> operations = redisTemplate .opsForValue(); 
	    result = operations.get(key); 
	    return result; 
	  } 
	 
	  /**
	   * set 
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:34
	   * @param key
	   * @param value
	   */
	  public void set(final String key, Object value) { 
	    try { 
	      ValueOperations<Serializable, Object> operations = redisTemplate .opsForValue(); 
	      operations.set(key, value); 
	    } catch (Exception e) { 
	       e.printStackTrace();
	    } 
	  } 
	  
	  /**
	   * set 
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:39
	   * @param key
	   * @param value
	   * @param ttl
	   */
	  public void set(final String key, Object value, Long ttl) { 
	      ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue(); 
	      operations.set(key, value, ttl, TimeUnit.SECONDS) ;
	      redisTemplate.expire(key, ttl, TimeUnit.SECONDS);//
	  } 
	  public boolean expireKey(final String key, Long ttl){
		  return  redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
	  }
	  
	  /**
	   * 清空redis当前db
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   */
	  public void flushDB(){
		  redisTemplate.execute(new RedisCallback<String>() {

				@Override
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					try {
						connection.flushDb();
						return "ok";
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
		    	
			});
	  }
	  /**
	   * 清空redis所有 db
	   * @author jelly
	   * @date 2018年10月10日 下午5:05:11
	   */
	  public void flushAll(){
		  redisTemplate.execute(new RedisCallback<String>() {

				@Override
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					try {
						connection.flushAll();
						return "ok";
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
		    	
		});
	  }


  
	  
}