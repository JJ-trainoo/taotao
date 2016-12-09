package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	EasyUIResult getItemList(Integer page, Integer rows);
	
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
