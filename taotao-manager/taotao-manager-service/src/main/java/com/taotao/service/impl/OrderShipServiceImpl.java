package com.taotao.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbOrderShippingExample;
import com.taotao.pojo.TbOrderShippingExample.Criteria;
import com.taotao.service.OrderShipService;

@Service
public class OrderShipServiceImpl implements OrderShipService {

	@Autowired
	private TbOrderShippingMapper orderShipMapper;
	
	@Override
	public EasyUIResult getItemList(Integer page, Integer rows, String orderId) {
		TbOrderShippingExample example = new TbOrderShippingExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(orderId)){
			criteria.andOrderIdEqualTo(orderId);
		}
		// 设置分页
		PageHelper.startPage(page, rows);
		// 添加查询条件
		List<TbOrderShipping> list = orderShipMapper.selectByExample(example);
		PageInfo<TbOrderShipping> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

}
