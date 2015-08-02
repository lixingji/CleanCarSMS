package com.cleancarSMS.service;

import com.cleancarSMS.dao.AdminDaoImpl;
import com.cleancarSMS.dao.IAdminDao;

public class AdminServiceImpl implements IAdminService {

	IAdminDao adminDao=new AdminDaoImpl();
	/**
	 * 登陆
	 */
	@Override
	public int login(String adminName, String Psw) {
		int status=adminDao.loadByNameAndPsw(adminName, Psw);
		adminDao=null;
		return status;
	}

	/**
	 * 更新服务金额
	 */
	@Override
	public int updateMoney(double money) {
		int status=adminDao.updateMoney(money);
		adminDao=null;
		return status;
	}

	/**
	 * 更新密码
	 */
	@Override
	public int updatePsw(String oldPsw,String newPsw, String adminName) {
		int status=adminDao.loadByNameAndPsw(adminName, oldPsw);
		if(status==1){
			status=adminDao.updatePsw(newPsw, adminName);
		}
		adminDao=null;
		return status;
	}

	@Override
	public int updateInOutMoney(double money) {
		int status=adminDao.updateInOutMoney(money);
		adminDao=null;
		return status;
	}

}
