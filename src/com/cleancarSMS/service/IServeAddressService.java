package com.cleancarSMS.service;

import java.util.List;

import com.cleancarSMS.pojo.ServeAddress;

public interface IServeAddressService {

	public int addAddress(int managerId,String serveAddress);
	public int deleteAddress(int id);
	public List<ServeAddress> loadAddressList(String province,String city);
	public List<ServeAddress> loadAddressListByMId(int managerId);
	
}
