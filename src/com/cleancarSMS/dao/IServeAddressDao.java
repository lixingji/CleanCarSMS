package com.cleancarSMS.dao;

import java.util.List;

import com.cleancarSMS.pojo.ServeAddress;

public interface IServeAddressDao {
	
	public int add(int managerId,String serveAddress);
	public int delete(int id);
	public List<ServeAddress> loadServeAddressList(String province,String city);
	public List<ServeAddress> loadServeAddressListByMId(int managerId);

}
