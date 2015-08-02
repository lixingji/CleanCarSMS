package com.cleancarSMS.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cleancarSMS.util.SignUtil;
import com.opensymphony.xwork2.ActionSupport;

public class InterfaceConfirmAction extends ActionSupport {

	/**
	 * 接受来自微信服务器的验证
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String GET = "GET";
	private static final String POST = "POST";
	
	// 获取请求和响应
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	private String signature;  //微信加密签名
	private String timestamp;  //时间戳
	private String nonce;      //随机数
	private String echostr;    //随机字符串
	
	@Override
	public String execute() throws Exception {
		
		/**
		 * get方法，用于验证服务器地址的有效性
		 */
		if (request.getMethod().equals(GET)) {
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.close();
				out = null;
			}
		} 
		/**
		 * post方法，消息的接收、处理、响应
		 */
		else if (request.getMethod().equals(POST)) {
			//TUDO 相应消息
		}
		return NONE;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
}
