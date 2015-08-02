package com.cleancarSMS.service;

import java.util.List;

import com.cleancarSMS.dao.IServeAddressDao;
import com.cleancarSMS.dao.ServeAddressDaoImpl;
import com.cleancarSMS.pojo.ServeAddress;

public class ServeAddressServiceImpl implements IServeAddressService{

	
	IServeAddressDao aervAddressDao=new ServeAddressDaoImpl();
	@Override
	public int addAddress(int managerId,String serveAddress) {
		return aervAddressDao.add(managerId,serveAddress);
	}

	@Override
	public int deleteAddress(int id) {
		return aervAddressDao.delete(id);
	}

	@Override
	public List<ServeAddress> loadAddressList(String province,String city) {
		return aervAddressDao.loadServeAddressList(province, city);
	}

	@Override
	public List<ServeAddress> loadAddressListByMId(int managerId) {
		// TODO Auto-generated method stub
		return aervAddressDao.loadServeAddressListByMId(managerId);
	}

}
