package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;

public interface OrderShipService {

	EasyUIResult getItemList(Integer page, Integer rows, String orderId);

}
