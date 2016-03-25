package com.netease.course.web.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author 公猴脖子男
 */
public class WebUtil {

	/**
	 * JSON转换器
	 */
	public static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	public static final ObjectWriter OBJECTWRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();

	/**
	 * 为客户端设置Cookie
	 * 
	 * @param response
	 * @param user
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 */
	public static <T> void setCookie(HttpServletResponse response, T obj)
			throws JsonProcessingException, UnsupportedEncodingException {

		String cookieName = "json" + obj.getClass().getSimpleName();
		// 将对象转换成JSON字符串，以便放入Cookie中
		String json = OBJECTWRITER.writeValueAsString(obj);

		// 将转换后的JSON字符串URL编码后放进新建的Cookie
		Cookie cookie = new Cookie(cookieName, URLEncoder.encode(json, "UTF-8"));

		// 设置Cookie有效期60秒
		cookie.setMaxAge(60);

		// 将设置好的Cookie添加至response
		response.addCookie(cookie);
	}

	/**
	 * 将Cookie中的json字符串转化成对象返回
	 * 
	 * @param cookies
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonCookie2Bean(Cookie[] cookies, Class<T> clazz) {
		if (cookies != null) {
			try {
				String cookieName = "json" + clazz.getSimpleName();
				for (Cookie cookie : cookies) {
					if (cookieName.equals(cookie.getName())) {
						// 获取Cookie
						String jsonUser = URLDecoder.decode(cookie.getValue(), "UTF-8");

						// 将获取的Cookie字符串转换成对象
						T obj = OBJECTMAPPER.readValue(jsonUser, clazz);
						return obj;
					}
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将对应的Cookie数组转换成Java bean
	 */
	public static <T> T cookie2Bean(Cookie[] cookies, Class<T> clazz) {
		try {
			if (cookies != null) {
				// 创建新实例，对应类必须有无参构造
				T object = clazz.newInstance();
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

	/**
	 * 校验字符串非空
	 * 
	 * @param data
	 * @return
	 */
	public static boolean validData(String data) {
		if (data != null && !"".equals(data.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 生成文件名称
	 * 
	 * @param filename
	 * @return
	 */
	public static String generateFilename(String filename) {
		return UUID.randomUUID().toString() + "__" + filename;
	}

	/**
	 * 生成子路径
	 * 
	 * @param path
	 * @param filename
	 * @return
	 */
	public static String generateSavePath(String path, String filename) {
		int hashcode = filename.hashCode(); // 121221
		int dir1 = hashcode & 15;
		int dir2 = (hashcode >> 4) & 0xf;

		String savepath = path + File.separator + dir1 + File.separator + dir2;
		File file = new File(savepath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return savepath;
	}

}
