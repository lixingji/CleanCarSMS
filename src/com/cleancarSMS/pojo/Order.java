package com.cleancarSMS.pojo;

/**
 * 订单实体类
 * @author vinxent
 *
 */
public class Order {
	private String id;         //商品编号
	private String address;    //地址
	private String carNumber;  //车牌号
	private String userName;   //服务时间
	private String serveTime;  //姓名
	private String orderTime;  //下单时间
	private String phoneNumber;   //手机号码
	private int orderState;   //订单状态。0:待支付；1:已支付；2:已取消
	private String brand;  //品牌车型
	private String remark;  //备注
	private float money;   //金额
	private int managerId;   //负责人id
	private int type;//服务类型 1为外洗  2为内外洗  默认外洗
	private String declare;//声明
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServeTime() {
		return serveTime;
	}
	public void setServeTime(String serveTime) {
		this.serveTime = serveTime;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDeclare() {
		return declare;
	}
	public void setDeclare(String declare) {
		this.declare = declare;
	}

}
