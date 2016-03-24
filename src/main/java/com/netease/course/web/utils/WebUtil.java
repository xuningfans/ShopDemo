package com.netease.course.web.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.Cookie;

/**
 * 将对应的Cookie数组转换成Java bean
 * 
 * @author 公猴脖子男
 */
public class WebUtil {

	public static <T> T cookie2Bean(Cookie[] cookies, Class<T> clazz) {
		try {
			// 创建新实例，对应类必须有无参构造
			T object = clazz.newInstance();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					// 拿到所有方法
					Method[] methods = clazz.getDeclaredMethods();
					for (Method method : methods) {
						// 将cookie名首字母大写后在前面加上set，如xxx-->setXxx
						String cookieMethodName = "set" + cookie.getName().substring(0, 1).toUpperCase()
								+ cookie.getName().substring(1);
						if (method.getName().equals(cookieMethodName)) {
							// 如果cookie称和bean的方法名相同，将Cookie值注入给实例对象
							method.invoke(object, cookie.getValue());
						}
					}
				}
				System.out.println(object);
				return object;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean validData(String data) {
		if (data != null && !"".equals(data.trim())) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 生成文件名称
	 * @param filename
	 * @return
	 */
	public static String generateFilename(String filename){
		return UUID.randomUUID().toString()+ "__" + filename;
	}
	
	/**
	 * 生成子路径
	 * @param path
	 * @param filename
	 * @return
	 */
	public static String generateSavePath(String path,String filename){
		int hashcode = filename.hashCode();  //121221
		int dir1 = hashcode&15;
		int dir2 = (hashcode>>4)&0xf;
		
		String savepath = path + File.separator + dir1 + File.separator + dir2;
		File file = new File(savepath);
		if(!file.exists()){
			file.mkdirs();
		}
		return savepath;
	}

}
