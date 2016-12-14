package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.utils.TaotaoResult;

public interface ContentCategoryService {

	List<EasyUITreeNode> getCategoryList(Long parentId);
	
	TaotaoResult createContentCategory(Long parentId, String name);
	
	TaotaoResult updateContentCategory(Long id, String name);

	TaotaoResult deleteContentCategory(Long parentId, Long id);
}
