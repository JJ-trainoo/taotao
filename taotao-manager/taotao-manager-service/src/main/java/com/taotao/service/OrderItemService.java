package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;

public interface OrderItemService {

	EasyUIResult getItemList(Integer page, Integer rows, String itemId, String orderId);

}
