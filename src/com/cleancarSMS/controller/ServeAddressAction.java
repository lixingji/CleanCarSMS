package com.cleancarSMS.controller;

import java.util.List;
import java.util.Map;

import com.cleancarSMS.pojo.ServeAddress;
import com.cleancarSMS.service.IServeAddressService;
import com.cleancarSMS.service.ServeAddressServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ServeAddressAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IServeAddressService serveAddressService = new ServeAddressServiceImpl();

	// context
	private ActionContext context;
	private Map<String, Object> session;

	private int status;
	private int id;
	private int managerId;
	private String serveAddress;
	private String province;
	private String city;
	private List<ServeAddress> serveAddressList;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServeAddress() {
		return serveAddress;
	}

	public void setServeAddress(String serveAddress) {
		this.serveAddress = serveAddress;
	}

	public List<ServeAddress> getServeAddressList() {
		return serveAddressList;
	}

	public void setServeAddressList(List<ServeAddress> serveAddressList) {
		this.serveAddressList = serveAddressList;
	}

	/**
	 * 添加服务地址
	 * 
	 * @return
	 */
	public String addAddress() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = serveAddressService.addAddress(managerId, serveAddress);
		} else {
			status = 0;// 未登录
		}
		return SUCCESS;
	}

	/**
	 * 删除服务地址
	 * 
	 * @return
	 */
	public String deleteAddress() {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		// 判断是否已经登录
		if (session.get("xicheAdmin") != null) {
			status = serveAddressService.deleteAddress(id);
		} else {
			status = 0;// 未登录
		}
		
		return SUCCESS;
	}

	/**
	 * 获取服务地址
	 * 
	 * @return
	 */
	public String getAddressList() {
		serveAddressList = serveAddressService.loadAddressList(province, city);
		return SUCCESS;
	}
	
	/**
	 * 根据managerId获取服务地址
	 * 
	 * @return
	 */
	public String getAddressListByMId() {
		serveAddressList = serveAddressService.loadAddressListByMId(managerId);
		return SUCCESS;
	}

}
