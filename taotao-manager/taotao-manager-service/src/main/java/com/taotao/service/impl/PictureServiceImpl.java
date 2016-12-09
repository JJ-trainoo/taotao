package com.taotao.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.PictureResult;
import com.taotao.service.PictureService;

/**
 * 图片上传
 * @ClassName: PictureServiceImpl 
 * @Description: TODO 
 * @author ZhouTao
 * @date 2016年12月8日 下午3:33:59 
 *
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_AADDRESS}")
	private String FTP_AADDRESS;
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile){
		// 上传文件功能实现
		String path = savePicture(uploadFile);
		// 回显
		PictureResult result = new PictureResult(0, IMAGE_BASE_URL + path);
		return result;
	}

	private String savePicture(MultipartFile uploadFile) {
		String result = null;
		try {
			// 上传文件功能实现
			// 判断文件是否为空
			if (uploadFile.isEmpty())
				return null;
			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
					+ new SimpleDateFormat("MM").format(new Date()) + "/"
					+ new SimpleDateFormat("dd").format(new Date());
			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			FtpUtil.uploadFile(FTP_AADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FILI_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			result = filePath + "/" + newFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
