package com.cleancarSMS.controller;

import java.util.Map;

import com.cleancarSMS.service.AdminServiceImpl;
import com.cleancarSMS.service.IAdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Admin管理员控制器
 * 
 * @author lixingji on date 2015/4/7
 */
public class AdminAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	// context
	private ActionContext context;
	private Map<String, Object> session;

	IAdminService adminService = new AdminServiceImpl();

	private int status = 0;
	private float money = 0;
	private String adminName;
	private String password, oldPsw, newPsw;

	

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPsw() {
		return oldPsw;
	}

	public void setOldPsw(String oldPsw) {
		this.oldPsw = oldPsw;
	}

	public String getNewPsw() {
		return newPsw;
	}

	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * 登陆 1成功，-1失败
	 */
	public String adminLogin() {
		status = adminService.login(adminName, password);
		if (status == 1) {
			this.context = ActionContext.getContext();
			this.session = this.context.getSession();
			session.put("xicheAdmin", adminName);
		}
		return SUCCESS;

	}

	/**
	 * 更新外洗服务金额 1成功，-1失败,0尚未登陆
	 */
	public String updateMoney() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = adminService.updateMoney(money);
		} else {
			status = 0;
		}
		return SUCCESS;

	}
	
	/**
	 * 更新内外洗车服务金额 1成功，-1失败,0尚未登陆
	 */
	public String updateInOutMoney() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = adminService.updateInOutMoney(money);
		} else {
			status = 0;
		}
		return SUCCESS;

	}

	/**
	 * 更新密码 1成功，-1失败
	 */
	public String updatePsw() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			adminName=(String)session.get("xicheAdmin");
			status = adminService.updatePsw(oldPsw, newPsw, adminName);
		} else {
			status = 0;
		}
		return SUCCESS;

	}
}
