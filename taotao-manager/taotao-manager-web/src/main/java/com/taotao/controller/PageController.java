package com.taotao.controller;

import com.taotao.common.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageController {
	
	/**
	 * 打开首页
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	} 
	
	@RequestMapping("/{page}")
	public String toPages(@PathVariable String page){
		return page;
	}

	@RequestMapping("/login/sign_in")
	@ResponseBody
	public TaotaoResult login(HttpServletRequest request) throws ServletException, IOException {
		TaotaoResult result = new TaotaoResult();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)
				&& userName.equals("admin") && password.equals("admin")){
			request.getSession().setAttribute("username", userName);
			System.out.println("success");
			result.setStatus(1);
			result.setMsg("登录成功");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("登录失败,用户名或密码错误！");
			return result;
		}
	}
}
