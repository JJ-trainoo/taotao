package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("INDEX_ITEM_REDIS_KEY")
	private String INDEX_ITEM_REDIS_KEY;
	
	@SuppressWarnings("rawtypes")
	@Override
	public CatResult getItemCatList() {
		//从缓存中获取
		try {
			String result = jedisClient.hGet(INDEX_ITEM_REDIS_KEY, "all");
			if (!StringUtils.isBlank(result)) {
				List list = JsonUtils.jsonToList(result, Object.class);
				CatResult catResult = new CatResult();
				catResult.setData(list);
				return catResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		//写入缓存
		try {
			String json = JsonUtils.objectToJson(catResult.getData());
			jedisClient.hSet(INDEX_ITEM_REDIS_KEY, "all", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return catResult;
	}

	/**
	 * 查询分类列表
	 * @authod zhoutao
	 * @param i
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<?> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList();
		int count = 0;
		for(TbItemCat tbItemCat : list){
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setData(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				//只显示前14个
				count ++;
				if(parentId == 0 && count >= 14){
					break;
				}
			//最后一个节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}
