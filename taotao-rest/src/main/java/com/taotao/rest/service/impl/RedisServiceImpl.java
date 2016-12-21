package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	/**
	 * 缓存同步，使用contentCids，是因为删除的时候可以删除多个，为了通用性 所以改成可以同步多个cid
	 */
	@Override
	public TaotaoResult syncContent(String contentCids) {
		try {
			String[] ids = contentCids.split(",");
			for (int i = 0; i < ids.length; i++) {
				jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, ids[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();

	}

}
