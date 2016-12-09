package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品 管理控制层
 * 
 * @ClassName: ItemController
 * @Description: TODO
 * @author ZhouTao
 * @date 2016年12月7日 下午5:00:41
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult getItemById(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "30") Integer rows) {
		return itemService.getItemList(page, rows);
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) throws Exception {
		// 添加商品信息
		itemService.createItem(item, desc, itemParams);
		return TaotaoResult.ok();
	}

}
