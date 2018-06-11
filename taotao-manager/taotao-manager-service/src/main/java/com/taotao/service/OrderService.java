package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;

public interface OrderService {

	EasyUIResult getItemList(Integer page, Integer rows, Long userId, String orderId);
	
}
