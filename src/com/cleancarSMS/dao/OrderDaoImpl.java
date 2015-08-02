package com.cleancarSMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cleancarSMS.pojo.Order;
import com.cleancarSMS.util.JDBC_Connect;

public class OrderDaoImpl implements IOrderDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 根据手机号码查询个人所有订单（0个或多个）
	 */
	@Override
	public List<Order> loadAllOrdersByPhoneNumber(String phoneNumber, int pageIndex, int pageSize) {
		
		int pageStart=pageIndex*pageSize;
		
		List<Order> orders = new ArrayList<Order>();
		conn = JDBC_Connect.getConnection();
		String sql = "select * from t_order where phoneNumber=? order by id desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性			
			pstmt.setString(1, phoneNumber);
			pstmt.setInt(2, pageStart);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				
				order.setId(rs.getString("id"));
				order.setAddress(rs.getString("address"));
				order.setCarNumber(rs.getString("carNumber"));
				order.setOrderTime(rs.getString("orderTime"));
				order.setPhoneNumber(rs.getString("phoneNumber"));
				order.setServeTime(rs.getString("serveTime"));
				order.setUserName(rs.getString("userName"));
				order.setBrand(rs.getString("brand"));
				order.setMoney(rs.getFloat("money"));
				order.setOrderState(rs.getInt("orderState"));
				order.setRemark(rs.getString("remark"));
				order.setType(rs.getInt("type"));
				order.setDeclare(rs.getString("declareded"));
				
				orders.add(order);
				order = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);
		}
		return orders;
	}

	/**
	 * 增加order, 成功返回id(大于0)，失败返回-1
	 */
	@Override
	public String addOrder(Order order) {
		String id = "0";
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "Insert into t_order(address,carNumber,orderTime,phoneNumber,serveTime,userName,orderState,brand,remark,money,id,managerId,type,declareded) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			// 设置字段属性
			pstmt.setString(1, order.getAddress());
			pstmt.setString(2, order.getCarNumber());
			pstmt.setString(3, order.getOrderTime());
			pstmt.setString(4, order.getPhoneNumber());
			pstmt.setString(5, order.getServeTime());
			pstmt.setString(6, order.getUserName());
			pstmt.setInt(7, order.getOrderState());
			pstmt.setString(8, order.getBrand());
			pstmt.setString(9, order.getRemark());
			pstmt.setFloat(10, order.getMoney());
			pstmt.setString(11, order.getId());
			pstmt.setInt(12, order.getManagerId());
			pstmt.setInt(13, order.getType());
			pstmt.setString(14, order.getDeclare());

			// 提交pstmt对象
			int retId = pstmt.executeUpdate();
			if (retId == 1) {
				id = order.getId();
			}
			System.out.println("添加成功，内容如下id=" + id + "：");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("添加失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return id;  //添加失败
	}

	/**
	 * 修改状态
	 */
	@Override
	public String updateByOrderState(int orderState, String id) {
		String Id = "0";   //返回的状态码
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "update t_order set orderState=? where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setInt(1, orderState);
			pstmt.setString(2, id);

			// 提交pstmt对象
			int retId = pstmt.executeUpdate();
			if (retId==1) {
				Id = id;
			}
			System.out.println("影响行数：" + retId + "：");
		} catch (Exception e) {
			System.out.println("修改失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		
		return Id;
	}

	/**
	 * 查询所需金额
	 */
	@Override
	public float queryMoney(int type) {
		float money = -1;   //支付费用
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql ="";
			//1外洗  2内外洗
			if(type==1){
				 sql="select money from t_money";
			}else{
				sql="select inoutmoney from t_money";
			}
			
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);

			// 提交pstmt对象
			rs = pstmt.executeQuery();
			if(rs.next()) {
				money = rs.getFloat(1);
			}
		} catch (Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return money;
	}

	@Override
	public Order queryDetailById(String id) {
		
		Order order = null;
		conn = JDBC_Connect.getConnection();
		String sql = "select * from t_order where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {	
				order = new Order();
				order.setId(rs.getString("id"));
				order.setAddress(rs.getString("address"));
				order.setCarNumber(rs.getString("carNumber"));
				order.setOrderTime(rs.getString("orderTime"));
				order.setPhoneNumber(rs.getString("phoneNumber"));
				order.setServeTime(rs.getString("serveTime"));
				order.setUserName(rs.getString("userName"));
				order.setBrand(rs.getString("brand"));
				order.setMoney(rs.getFloat("money"));
				order.setOrderState(rs.getInt("orderState"));
				order.setRemark(rs.getString("remark"));
				order.setManagerId(rs.getInt("managerId"));
				order.setType(rs.getInt("type"));
				order.setDeclare(rs.getString("declareded"));
			} else {
				System.out.println("查不到数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);
		}
		return order;
	}

	@Override
	public String updateOrder(Order order) {
		String Id = "0";   //返回的状态码
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "update t_order set address=?,carNumber=?,serveTime=?,userName=?,brand=?,remark=? where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			
			pstmt.setString(1, order.getAddress());
			pstmt.setString(2, order.getCarNumber());
			pstmt.setString(3, order.getServeTime());
			pstmt.setString(4, order.getUserName());
			pstmt.setString(5, order.getBrand());
			pstmt.setString(6, order.getRemark());
			pstmt.setString(7, order.getId());

			// 提交pstmt对象
			int retId = pstmt.executeUpdate();
			if (retId==1) {
				Id = order.getId();
			}
			System.out.println("影响行数：" + retId + "：");
		} catch (Exception e) {
			System.out.println("修改失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		
		return Id;
	}

}
