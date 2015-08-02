package com.cleancarSMS.service;

import java.util.List;

import com.cleancarSMS.dao.IOrderDao;
import com.cleancarSMS.dao.OrderDaoImpl;
import com.cleancarSMS.pojo.Order;

public class OrderServiceImpl implements IOrderService {
	
	//新建dao实例
	private IOrderDao orderDao = new OrderDaoImpl();

	@Override
	public List<Order> loadAllOrdersByPhoneNumber(String phoneNumber, int pageIndex, int pageSize) {
		return orderDao.loadAllOrdersByPhoneNumber(phoneNumber, pageIndex, pageSize);
	}

	@Override
	public String addOrder(Order order) {
		return orderDao.addOrder(order);
	}

	@Override
	public String updateByOrderState(int orderState, String id) {
		return orderDao.updateByOrderState(orderState, id);
	}

	@Override
	public float getPayMoney(int type) {
		return orderDao.queryMoney(type);
	}

	@Override
	public Order queryDetailById(String id) {
		// TODO Auto-generated method stub
		return orderDao.queryDetailById(id);
	}

	@Override
	public String updateOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.updateOrder(order);
	}

}
