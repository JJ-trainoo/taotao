package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getCategoryList(Long parentId) {
		// 根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			// 创建一个节点
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setParentId(tbContentCategory.getParentId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");

			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		// 创建对象，初始化值
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 添加记录
		contentCategoryMapper.insert(contentCategory);
		// 查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult updateContentCategory(Long id, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult deleteContentCategory(Long parentId, Long id) {
		//删除节点
		deleteCategory(id);
		//判断父节点下是否还有自子节点。
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> resultList = contentCategoryMapper.selectByExample(example);
		if(resultList == null || resultList.size() <= 0){
			TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
			parentCat.setIsParent(false);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok();
	}

	/**
	 * 递归删除节点
	 * @Title: deleteCategory 
	 * @Description: TODO
	 * @param @param id
	 * @return void
	 * @throws
	 */
	private void deleteCategory(Long id) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategoryMapper.deleteByPrimaryKey(id);
		if(contentCategory.getIsParent()){
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(id);
			List<TbContentCategory> resultList = contentCategoryMapper.selectByExample(example);
			if(resultList != null && resultList.size() > 0){
				for (TbContentCategory cat : resultList){
					deleteCategory(cat.getId());
				}
			}
		}
	}

}
