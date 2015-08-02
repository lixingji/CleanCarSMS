package com.cleancarSMS.controller;

import java.util.Map;

import com.RestTest;
import com.cleancarSMS.pojo.AreaManager;
import com.cleancarSMS.service.AreaManagerServiceImpl;
import com.cleancarSMS.service.IAreaManagerService;
import com.cleancarSMS.service.IOrderService;
import com.cleancarSMS.service.OrderServiceImpl;
import com.cleancarSMS.util.MobileCode;
import com.cleancarSMS.util.MyAccount;
import com.opensymphony.xwork2.ActionContext;

public class CleanCarSMSAction {
	// 上传参数
	private String mobile;
	private String msgCode;
	private String userName;
	private String userPhone;
	private String carNum;
	private String address;
	private String orderId;
	private int managerId;// 负责人id
	// 返回参数
	private String status;

	// 帐号常量
	private String accountSid = MyAccount.getAccountsid();
	private String authToken = MyAccount.getToken();
	private String appId = MyAccount.getAppid();
	private String templateId = MyAccount.getTemplateid();
	private String managerTemplateId = MyAccount.getManagertemplateid();

	// context
	private ActionContext context;
	private Map<String, Object> session;

	// 向服务器获取验证码 一小时内超过5次时就禁止再次发送
	public String getSMSCode() {
		String randomCode = MobileCode.randomString(6);
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();

		if (session.get("count") == null) {
			session.put("count", 1);
			status = RestTest.testTemplateSMS(true, accountSid, authToken,
					appId, templateId, mobile, randomCode);
		} else {
			int count = (Integer) session.get("count");
			if (count != 5) {
				session.put("count", count + 1);
				status = RestTest.testTemplateSMS(true, accountSid, authToken,
						appId, templateId, mobile, randomCode);
			} else {
				status = "0";// status为0表示此session超过5次
			}
		}

		session.put("msgCode", randomCode);

		System.out.println("msgcode:" + session.get("msgCode"));
		return "success";
	}

	// 手机端上传验证码
	public String sendSMSCode() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		String code = (String) session.get("msgCode");
		System.out.println("code:" + code);
		status = "-1";
		if (code != null) {
			if (code.equals(msgCode)) {
				status = "1";
				session.put("xiche_userLogin", "yes");
			}
		}
		// 测试用
		if (msgCode.equals("888666")) {
			status = "1";
		}
		return "success";
	}

	// 向负责人发送服务短信
	public String sendSMStoManager() {
		// 发送短信
		String params = "";
		params = userName + "," + userPhone + "," + carNum + "," + address;
		System.out.println("参数：" + params);
		String sermobile = getManagerMobile();
		status = RestTest.testTemplateSMS(true, accountSid, authToken, appId,
				managerTemplateId, sermobile, params);

		return "success";
	}

	// 获取电话号码
	public String getManagerMobile() {
		// managerId
		String retMobile = "13808800087";//13808800087 总负责人，曾锐
		IAreaManagerService areaManagerService = new AreaManagerServiceImpl();
		AreaManager am = areaManagerService.loadById(managerId);
		if (am != null)
			retMobile = am.getManagerPhone();
		return retMobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

}
