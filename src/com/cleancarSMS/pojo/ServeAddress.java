package com.cleancarSMS.pojo;

public class ServeAddress {
	
	private int id; //服务id
	private String serveAddress;//服务地址
	private int managerId;//负责人id
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
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	
}
