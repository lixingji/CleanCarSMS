package com.cleancarSMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cleancarSMS.pojo.ServeAddress;
import com.cleancarSMS.util.JDBC_Connect;

public class ServeAddressDaoImpl implements IServeAddressDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public int add(int managerId,String serveAddress) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "Insert into t_serveaddress(managerId,serveAddress) values(?,?)";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setInt(1, managerId);
			pstmt.setString(2, serveAddress);

			// 提交pstmt对象
			pstmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("添加失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return -1;  //添加失败
	}

	@Override
	public int delete(int id) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "delete from t_serveaddress where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setInt(1, id);
			// 提交pstmt对象
			pstmt.executeUpdate();
			System.out.println("删除成功");
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("删除失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return -1;
	}

	@Override
	public List<ServeAddress> loadServeAddressList(String province,String city) {
		List<ServeAddress> list = new ArrayList<ServeAddress>();
		conn = JDBC_Connect.getConnection();
		String sql = "select id,serveAddress,managerId from t_serveaddress where managerId in(select id from t_areamanager where province=? and city=?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, province);
			pstmt.setString(2, city);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ServeAddress serveAddress = new ServeAddress();
				serveAddress.setId(rs.getInt("id"));
				serveAddress.setServeAddress(rs.getString("serveAddress"));
				serveAddress.setManagerId(rs.getInt("managerId"));
				list.add(serveAddress);
				serveAddress = null;
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);
		}
		return null;
	}

	@Override
	public List<ServeAddress> loadServeAddressListByMId(int managerId) {
		List<ServeAddress> list = new ArrayList<ServeAddress>();
		conn = JDBC_Connect.getConnection();
		String sql = "select id,serveAddress from t_serveaddress where managerId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ServeAddress serveAddress = new ServeAddress();
				serveAddress.setId(rs.getInt("id"));
				serveAddress.setServeAddress(rs.getString("serveAddress"));
				list.add(serveAddress);
				serveAddress = null;
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);
		}
		return null;
	}

}
