package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.PictureResult;
import com.taotao.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;

	@RequestMapping("/upload")
	@ResponseBody
	public String uploda(MultipartFile uploadFile) {
		//调用service上传图片
		PictureResult pictureResult = pictureService.uploadPicture(uploadFile);
		//返回上传结果
		//解决火狐兼容问题，返回对象的话火狐会上传失败,所以这里返回String
		String json = JsonUtils.objectToJson(pictureResult);
		return json;
	}
}

