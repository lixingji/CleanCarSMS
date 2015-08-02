package com.cleancarSMS.test;

import java.util.Date;
import java.util.List;

import com.cleancarSMS.pojo.Order;
import com.cleancarSMS.service.IOrderService;
import com.cleancarSMS.service.OrderServiceImpl;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setAddress("china");
		order.setCarNumber("12345");
		order.setOrderTime(new Date().toString());
		order.setPhoneNumber("1234567890");
		order.setServeTime(new Date().toString());
		order.setUserName("hahahah");
		
		IOrderService service = new OrderServiceImpl();
//		int id = service.addOrder(order);
//		System.out.println(id);
		
		List<Order> orders = service.loadAllOrdersByPhoneNumber("1234567890",1,2);
		for(Order ord: orders) {
			System.out.println("用户名：" + ord.getUserName());
		}
	}

}
