package com.taotao.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.pojo.TbOrderItemExample;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemExample.Criteria;
import com.taotao.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private TbOrderItemMapper orderItemMapper;

	@Override
	public EasyUIResult getItemList(Integer page, Integer rows, String itemId, String orderId) {
		TbOrderItemExample example = new TbOrderItemExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(itemId)){
			criteria.andItemIdEqualTo(itemId);
		}
		if (StringUtils.isNotBlank(orderId)){
			criteria.andOrderIdEqualTo(orderId);
		}
		// 设置分页
		PageHelper.startPage(page, rows);
		// 添加查询条件
		List<TbOrderItem> list = orderItemMapper.selectByExample(example);
		PageInfo<TbOrderItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

}
