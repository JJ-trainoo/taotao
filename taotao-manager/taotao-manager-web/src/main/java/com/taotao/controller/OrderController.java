package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.service.OrderService;

/**
 * 订单 管理控制层
 * @author zhoutao
 * @date 2018年6月10日
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult getItemById(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "30") Integer rows,
			@RequestParam(defaultValue = "0") Long userId,
			@RequestParam(defaultValue = "") String orderId) {
		return orderService.getItemList(page, rows, userId, orderId);
	}

}
