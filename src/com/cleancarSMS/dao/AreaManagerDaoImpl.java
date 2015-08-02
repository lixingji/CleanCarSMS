package com.cleancarSMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cleancarSMS.pojo.AreaManager;
import com.cleancarSMS.util.JDBC_Connect;

public class AreaManagerDaoImpl implements IAreaManagerDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public int add(AreaManager am) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "Insert into t_areamanager(province,city,managerName,managerPhone) values(?,?,?,?)";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setString(1, am.getProvince());
			pstmt.setString(2, am.getCity());
			pstmt.setString(3, am.getManagerName());
			pstmt.setString(4, am.getManagerPhone());

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
	public int update(AreaManager am) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "update t_areamanager set province=?,city=?,managerName=?,managerPhone=? where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置字段属性
			pstmt.setString(1, am.getProvince());
			pstmt.setString(2, am.getCity());
			pstmt.setString(3, am.getManagerName());
			pstmt.setString(4, am.getManagerPhone());
			pstmt.setInt(5, am.getId());

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
	public AreaManager loadById(int id) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "select * from t_areamanager where id=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			// 提交pstmt对象
			rs = pstmt.executeQuery();
			if(rs.next()) {
				AreaManager am=new AreaManager();
				am.setId(rs.getInt("id"));
				am.setProvince(rs.getString("province"));
				am.setCity(rs.getString("city"));
				am.setManagerName(rs.getString("managerName"));
				am.setManagerPhone(rs.getString("managerPhone"));
				return am;
			}
		} catch (Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return null;
	}

	@Override
	public List<AreaManager> loadByProvince(String province) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "select * from t_areamanager where province=?";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, province);

			// 提交pstmt对象
			rs = pstmt.executeQuery();
			List<AreaManager> list=new ArrayList<AreaManager>();
			while(rs.next()) {
				AreaManager am=new AreaManager();
				am.setId(rs.getInt("id"));
				am.setProvince(rs.getString("province"));
				am.setCity(rs.getString("city"));
				am.setManagerName(rs.getString("managerName"));
				am.setManagerPhone(rs.getString("managerPhone"));
				list.add(am);
				am=null;
			}
			return list;
		} catch (Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return null;
	}

	@Override
	public List<AreaManager> loadAll() {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "select * from t_areamanager";
			// 获取Statement对象
			pstmt = conn.prepareStatement(sql);

			// 提交pstmt对象
			rs = pstmt.executeQuery();
			List<AreaManager> list=new ArrayList<AreaManager>();
			while(rs.next()) {
				AreaManager am=new AreaManager();
				am.setId(rs.getInt("id"));
				am.setProvince(rs.getString("province"));
				am.setCity(rs.getString("city"));
				am.setManagerName(rs.getString("managerName"));
				am.setManagerPhone(rs.getString("managerPhone"));
				list.add(am);
				am=null;
			}
			return list;
		} catch (Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		} finally {
			JDBC_Connect.free(rs, conn, pstmt);// 释放连接
		}
		return null;
	}

	@Override
	public int delete(int id) {
		try {
			// 连接数据库
			conn = JDBC_Connect.getConnection();
			// 添加数据的SQL语句
			String sql = "delete from t_areamanager where id=?";
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

}
