package com.cleancarSMS.dao;

import java.util.List;

import com.cleancarSMS.pojo.Order;

public interface IOrderDao {
	
	/**
	 * 查询记录
	 * 
	 * @param phoneNumber 手机号码
	 * @param pageIndex  页码
	 * @param pageSize   每页数量
	 * @return
	 */
	public List<Order> loadAllOrdersByPhoneNumber(String phoneNumber, int pageIndex, int pageSize);
	
	/**
	 * 添加记录
	 * 
	 * @param order 订单详情
	 * @return
	 */
	public String addOrder(Order order);
	
	/**
	 * 修改记录
	 * @param orderState 订单状态
	 * @return
	 */
	public String updateByOrderState(int orderState, String id);
	
	/**
	 * 查询支付金额
	 * @return
	 */
	public float queryMoney(int type);
	
	/**
	 * 查询订单详情
	 * @param id
	 * @return
	 */
	public Order queryDetailById(String id);
	
	
	/**
	 * 更新订单
	 * @param order
	 * @return
	 */
	public String updateOrder(Order order);
	
}
