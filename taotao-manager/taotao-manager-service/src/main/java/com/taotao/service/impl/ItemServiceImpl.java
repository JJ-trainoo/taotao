package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * 
 * @ClassName: ItemServiceImpl
 * @Description: TODO
 * @author ZhouTao
 * @date 2016年12月7日 下午3:37:20
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public EasyUIResult getItemList(Integer page, Integer rows) {
		TbItemExample example = new TbItemExample();
		// 设置分页
		PageHelper.startPage(page, rows);
		// 添加查询条件
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc) {
		Date date = new Date();
		Long id = IDUtils.genItemId();
		item.setId(id);
		// 商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		// 添加商品描述
		// 创建TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		// 获得一个商品id
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 插入数据
		itemDescMapper.insert(itemDesc);

		return TaotaoResult.ok();
	}

}
