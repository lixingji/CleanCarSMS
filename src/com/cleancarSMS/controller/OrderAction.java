package com.cleancarSMS.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.RestTest;
import com.cleancarSMS.pojo.Order;
import com.cleancarSMS.service.IOrderService;
import com.cleancarSMS.service.OrderServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {

	private String id; // 商品id
	private String address; // 地址
	private String carNumber; // 车牌号
	private String userName; // 姓名
	private String serveTime; // 服务时间
	private int timeNum;//服务时间段数字
	private String phoneNumber; // 手机号码
	private int orderState = 0; // 订单状态。0:待支付；1:已支付；2:已取消
	private String brand; // 品牌车型
	private String remark; // 备注
	private float money = -1; // 金额
	private int type = 1;// 服务类型 1为外洗 2为内外洗 默认外洗
	private String declare;// 声明
	private int managerId; // 区域负责人id。

	private List<Order> orders; // 订单s
	private Order order;

	private int pageIndex = 0; // 页码，默认从0开始
	private int pageSize = 10; // 每一页的数量，默认是10

	// context
	private ActionContext context;
	private Map<String, Object> session;

	IOrderService service = new OrderServiceImpl();

	private static final long serialVersionUID = 1L;

	/**
	 * 查询某个特定用户的所有订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadAllOrdersByPhoneNumber() throws Exception {

		orders = service.loadAllOrdersByPhoneNumber(phoneNumber, pageIndex,
				pageSize);
		return SUCCESS;
	}

	/**
	 * 增加某个用户订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addOrder() throws Exception {
		long shopid = System.currentTimeMillis();
		order = new Order();
		order.setId("MSN" + shopid);
		order.setAddress(address);
		order.setCarNumber(carNumber);
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currDate = sim.format(new Date());
		System.out.println("当前时间：" + currDate);
		order.setOrderTime(currDate);
		order.setPhoneNumber(phoneNumber);
		order.setServeTime(serveTime);
		order.setUserName(userName);
		order.setBrand(brand);
		order.setMoney(service.getPayMoney(type));
		order.setOrderState(0);
		order.setRemark(remark);
		order.setManagerId(managerId);
		order.setType(type);
		order.setDeclare(declare);

		id = service.addOrder(order);

		return SUCCESS;
	}

	/**
	 * 支付订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String payOrder() throws Exception {
		order = service.queryDetailById(id);
		int state = order.getOrderState(); // 订单状态
		int type = order.getType();
		if (money != service.getPayMoney(type)) {
			System.out.println("金额不对");
			id = "0";
		} else if (state == 1) {
			System.out.println("不能重复支付");
			id = "0";
		} else if (state == 2) {
			System.out.println("已取消的订单不能支付");
			id = "0";
		} else {
			id = service.updateByOrderState(1, id);
		}
		return SUCCESS;
	}

	/**
	 * 取消订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrder() throws Exception {

		order = service.queryDetailById(id);

		if (order != null) { // 不为空的情况下
			int state = order.getOrderState(); // 订单状态
			System.out.println(state);
			if (state == 1) {
				System.out.println("已支付的订单不能取消");
				id = "0";
			} else if (state == 2) {
				System.out.println("已经取消过啦");
				id = "0";
			} else {
				id = service.updateByOrderState(2, id);
			}
		} else {
			System.out.println("无该订单,无法取消");
			id = "0";
		}

		return SUCCESS;
	}

	/**
	 * 查询支付金额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryMoney() throws Exception {
		money = service.getPayMoney(type);
		return SUCCESS;
	}

	/**
	 * 查询订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryDetail() throws Exception {
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		order = service.queryDetailById(id);
		//获取洗车费用， 写入session
		session.put("xiche_Money", order.getMoney());
		return SUCCESS;
	}

	/**
	 * 修改订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateOrder() throws Exception {

		order = service.queryDetailById(id); // 查询订单

		if (order != null) { // 不为空的情况下
			int state = order.getOrderState(); // 订单状态
			System.out.println(state);
			if (state == 1) {
				System.out.println("已支付的订单不能更新");
				id = "0";
			} else if (state == 2) {
				System.out.println("已取消的订单不能更新");
				id = "0";
			} else {
				order.setAddress(address);
				order.setCarNumber(carNumber);
				order.setServeTime(serveTime);
				order.setUserName(userName);
				order.setBrand(brand);
				order.setRemark(remark);
				id = service.updateOrder(order);
			}
		} else {
			System.out.println("无该订单,不能更新");
			id = "0";
		}
		return SUCCESS;
	}

	/**
	 * 修改订单状态
	 * @return
	 */
	public String updateOrderState() {
		String state = "0";
		// 修改订单支付状态
		state = service.updateByOrderState(1, id);// 将订单状态改为已支付
		return "success";
	}
	
	/**
	 * 获取预约时间段
	 * @param timeNum
	 */
	public String getSubscribeTime(){
		return SUCCESS;
	}
	
	/**
	 * 判断某个时间是否可以预约
	 * @param timeNum
	 **/
	public String getSubscribe(){
		return SUCCESS;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTimeNum() {
		return timeNum;
	}

	public void setTimeNum(int timeNum) {
		this.timeNum = timeNum;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setServeTime(String serveTime) {
		this.serveTime = serveTime;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public String getId() {
		return id;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMoney() {
		return money;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
