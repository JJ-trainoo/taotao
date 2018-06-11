package com.taotao.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderExample;
import com.taotao.pojo.TbOrderExample.Criteria;
import com.taotao.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	
	@Override
	public EasyUIResult getItemList(Integer page, Integer rows, Long userId, String orderId) {
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		if (userId != 0){
			criteria.andUserIdEqualTo(userId);
		}
		if (StringUtils.isNotBlank(orderId)){
			criteria.andOrderIdEqualTo(orderId);
		}
		// 设置分页
		PageHelper.startPage(page, rows);
		// 添加查询条件
		List<TbOrder> list = orderMapper.selectByExample(example);
		PageInfo<TbOrder> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

}
