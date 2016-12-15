package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	EasyUIResult queryContentListByCatId(Long id, Integer page, Integer rows);
	TaotaoResult insertContent(TbContent content);
	TaotaoResult deleteContentByCatId(String ids);
}
