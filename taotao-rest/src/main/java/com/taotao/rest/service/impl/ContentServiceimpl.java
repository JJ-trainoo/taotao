package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceimpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("INDEX_CONTENT_REDIS_KEY")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentListByContentId(Long contentId) {
		// 从缓存中取内容
		try {
			String result = jedisClient.hGet(INDEX_CONTENT_REDIS_KEY, contentId + "");
			if (!StringUtils.isBlank(result)) {
				// 把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentId);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);

		// 向缓存中存入
		try {
			// 把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hSet(INDEX_CONTENT_REDIS_KEY, contentId + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
