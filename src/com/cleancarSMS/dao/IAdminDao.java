package com.cleancarSMS.dao;

public interface IAdminDao {
	public int loadByNameAndPsw(String adminName,String password);
	public int updateMoney(double money);
	public int updateInOutMoney(double money);
	public int updatePsw(String psw,String adminName);
}
