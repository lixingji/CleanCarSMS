package com.cleancarSMS.service;

import java.util.List;

import com.cleancarSMS.dao.AreaManagerDaoImpl;
import com.cleancarSMS.dao.IAreaManagerDao;
import com.cleancarSMS.pojo.AreaManager;

public class AreaManagerServiceImpl implements IAreaManagerService{

	IAreaManagerDao areaManagerDao=new AreaManagerDaoImpl();
	@Override
	public int addManager(AreaManager am) {
		int status=areaManagerDao.add(am);
		areaManagerDao=null;
		return status;
	}

	@Override
	public int updateManager(AreaManager am) {
		int status=areaManagerDao.update(am);
		areaManagerDao=null;
		return status;
	}

	@Override
	public List<AreaManager> loadAll() {
		return areaManagerDao.loadAll();
	}

	@Override
	public AreaManager loadById(int id) {
		// TODO Auto-generated method stub
		return areaManagerDao.loadById(id);
	}

	@Override
	public int deleteManager(int id) {
		return areaManagerDao.delete(id);
	}

}
