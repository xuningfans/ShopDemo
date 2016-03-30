package com.netease.course.web.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.netease.course.meta.Product;

/**
 * 文件工具类
 *
 * @author 公猴脖子男
 */
public class MyFileUtils {

	/****************************************************
	 * 将request中的文件保存，并将保存路径封装到Product中
	 * 
	 * @param rootPath
	 * @param product
	 * @param request
	 * @throws IOException
	 ****************************************************/
	public static void saveFile(String rootPath, Product product, HttpServletRequest request) throws IOException {

		// 将request转换成multiRequest
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		// 获取出multiRequest中的所有文件名
		Iterator<String> iterator = multiRequest.getFileNames();

		while (iterator.hasNext()) {
			// 获取每个文件名
			String upFileName = iterator.next();
			// 通过文件名获取每个文件
			MultipartFile multipartFile = multiRequest.getFile(upFileName);
			// 如果没有文件，跳过此次循环
			if (multipartFile.isEmpty()) {
				product.setProductImage("");
				continue;
			}
			// 获取当前时间戳
			String timeStamp = String.valueOf(new Date().getTime());
			// 根据时间戳随机生成二级目录 如：7/8 3/1
			String savePath = rootPath + File.separator + "resources" + File.separator + "images" + File.separator
					+ timeStamp.substring(timeStamp.length() - 2, timeStamp.length() - 1) + File.separator
					+ timeStamp.substring(timeStamp.length() - 1);

			// 创建生成的目录
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 生成最终储存的文件名，加上时间戳防重名，分隔符使用'__'
			String fullSavePath = savePath + File.separator + timeStamp + "__" + multipartFile.getOriginalFilename();

			// 通过流保存上传文件
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(fullSavePath));

			// 封装保存路径
			product.setProductImage(fullSavePath.replace(rootPath, ""));
			product.setIsSell(true);
		}
	}
}
