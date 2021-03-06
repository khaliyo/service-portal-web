package com.ai.paas.ipaas.storm.sys.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


/**
 * 响应请求的工具类
 * @author Dongteng
 */
public class HttpResponseUtils {
	private static HttpServletRequest request;
	private HttpSession session;
	/**
	 * 响应客户端结果 成功、失败、错误
	 */
	private static final String RES_RESULT = "RES_RESULT";
	/**
	 * 响应客户端结果描述
	 */
	private static final String RES_MSG = "RES_MSG";
	/**
	 * 响应客户端数据
	 */
	private static final String RES_DATA = "RES_DATA";
	/**
	 * Exception Stack
	 */
	private static final String RES_ES = "RES_ES";
	/**
	 * 响应客户端结果 成功
	 */
	private static final String SUCCESS = "SUCCESS";
	/**
	 * 响应客户端结果 失败
	 */
	private static final String FAILED = "FAILED";
	/**
	 * 响应客户端结果 错误
	 */
	private static final String ERROR = "ERROR";

	/**
	 * json响应客户端成功
	 */
	public static void responseSuccess(HttpServletResponse response, String msg, JSONObject data) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(RES_RESULT, SUCCESS);
			jsonObject.put(RES_MSG, msg);
			jsonObject.put(RES_DATA, data);
			printWriter.write(jsonObject.toString());
			printWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	/**
	 * json响应客户端失败
	 */
	public static void responseFailed(HttpServletResponse response, String msg, JSONObject data) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(RES_RESULT, FAILED);
			jsonObject.put(RES_MSG, msg);
			jsonObject.put(RES_DATA, data);
			printWriter.write(jsonObject.toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	/**
	 * json响应客户端错误信息
	 */
	public static void responseError(HttpServletResponse response, String msg, Exception stackException) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(RES_RESULT, ERROR);
			jsonObject.put(RES_MSG, msg);
			jsonObject.put(RES_ES, stackException.getStackTrace());
			jsonObject.put(RES_DATA, stackException.getMessage());
			printWriter.write(jsonObject.toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	/**
	 * json响应客户端true
	 */
	public static void responseTrue(HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			printWriter.write("true");
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	/**
	 * json响应客户端false
	 */
	public static void responseFalse(HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			printWriter.write("false");
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	/**
	 * 页面记录异常信息
	 */
	public static void markException(String msg, Exception e) {
		request.setAttribute("es", msg);
		request.setAttribute("exception", e);
	}
	/**
	 * 获取客户端IP
	 */
	public static String getClientIp(){
		return request.getRemoteAddr();
	}
}
