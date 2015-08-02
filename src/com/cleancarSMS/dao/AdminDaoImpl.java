package com.cleancarSMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cleancarSMS.util.JDBC_Connect;

public class AdminDaoImpl implements IAdminDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	/**
	 * 登陆是否合法
	 */
	@Override
	public int loadByNameAndPsw(String adminName, String password) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "select id from t_admin where adminName=? and password=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminName);
			pstmt.setString(2, password);

			// 提交pstmt对象
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return -1;
	}
	/**
	 * 更新服务金额
	 */
	@Override
	public int updateMoney(double money) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "update t_money set money=? where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setDouble(1, money);
			pstmt.setInt(2, 1);

			// 提交pstmt对象
			pstmt.executeUpdate();
			return 1;

		} catch (Exception e) {
			System.out.println("修改失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		
		return -1;
	}
	/**
	 * 修改密码
	 */
	@Override
	public int updatePsw(String psw,String adminName) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "update t_admin set password=? where adminName=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setString(1, psw);
			pstmt.setString(2, adminName);

			// 提交pstmt对象
			pstmt.executeUpdate();
			return 1;

		} catch (Exception e) {
			System.out.println("修改失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		
		return -1;
	}
	@Override
	public int updateInOutMoney(double money) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "update t_money set inoutmoney=? where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setDouble(1, money);
			pstmt.setInt(2, 1);

			// 提交pstmt对象
			pstmt.executeUpdate();
			return 1;

		} catch (Exception e) {
			System.out.println("修改失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		
		return -1;
	}

}
