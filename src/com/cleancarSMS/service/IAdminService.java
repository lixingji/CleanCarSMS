package com.cleancarSMS.service;

public interface IAdminService {

	public int login(String adminName,String Psw);//登陆
	public int updateMoney(double money);//更新服务金额
	public int updateInOutMoney(double money);//更新内外洗车服务金额
	public int updatePsw(String oldPsw,String newPsw,String adminName);//更新密码
}
