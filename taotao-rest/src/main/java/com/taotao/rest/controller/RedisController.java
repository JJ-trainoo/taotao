package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	/**
	 * 可支持接收多个contentId.批量删除缓存数据
	 * @authod zhoutao
	 * @param contentCids
	 * @return
	 */
	@RequestMapping("/content/{contentCids}")
	public TaotaoResult contentCacheSync(@PathVariable String contentCids) {
		TaotaoResult result = redisService.syncContent(contentCids);
		return result;
	}
}
