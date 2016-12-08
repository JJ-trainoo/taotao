package com.taotao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class testFtpClient {
	
	@Test
	public void testFtp() throws Exception {
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建 ftp链接，默认是21端口
		ftpClient.connect("192.168.72.101", 21);
		//登录ftp服务器,使用用户名跟密码
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\tx.jpg"));
		//设置路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("test.jpg", inputStream);
		//关闭链接
		inputStream.close();
		ftpClient.logout();
	}
}
