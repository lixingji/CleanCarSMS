package com.cleancarSMS.service;

import java.util.List;

import com.cleancarSMS.pojo.Order;

public interface IOrderService {
	
	public List<Order> loadAllOrdersByPhoneNumber(String phoneNumber, int pageIndex, int pageSize);
	
	public String addOrder(Order order);
	
	public String updateByOrderState(int orderState, String id);
	
	public float getPayMoney(int type);
	
	public Order queryDetailById(String id);
	
	public String updateOrder(Order order);
}
