package com.cleancarSMS.controller;

import java.util.List;
import java.util.Map;

import com.cleancarSMS.pojo.AreaManager;
import com.cleancarSMS.service.AreaManagerServiceImpl;
import com.cleancarSMS.service.IAreaManagerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AreaManagerAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	// context
	private ActionContext context;
	private Map<String, Object> session;

	IAreaManagerService areaManagerService = new AreaManagerServiceImpl();

	private int status;
	private int id;
	private AreaManager am;
	private List<AreaManager> amList;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AreaManager getAm() {
		return am;
	}

	public void setAm(AreaManager am) {
		this.am = am;
	}
	

	public List<AreaManager> getAmList() {
		return amList;
	}

	/**
	 * 添加区域负责人
	 * 管理员权限
	 */
	public String addManager() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = areaManagerService.addManager(am);
		} else {
			status = 0;
		}
		return SUCCESS;
	}

	/**
	 * 修改区域负责人
	 * 管理员权限
	 */
	public String updateManager() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = areaManagerService.updateManager(am);
		} else {
			status = 0;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除区域负责人
	 * 管理员权限
	 */
	public String deleteManager() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = areaManagerService.deleteManager(id);
		} else {
			status = 0;
		}
		return SUCCESS;
	}


	/**
	 * 加载所有区域负责人
	 */
	public String loadManagerList() {
		amList=areaManagerService.loadAll();
		return SUCCESS;
	}

}
