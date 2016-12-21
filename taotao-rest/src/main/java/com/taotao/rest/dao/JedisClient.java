package com.taotao.rest.dao;

public interface JedisClient {
	
	String get(String key);
	String set(String key, String value);
	String hGet(String hkey, String key);
	long hSet(String hkey, String key, String value);
	long incr(String key);
	long expire(String key, int seconds);
	long ttl(String key);
	
	long del(String key);
	long hdel(String hkey, String key);
}
