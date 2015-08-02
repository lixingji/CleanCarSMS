package com.cleancarSMS.service;

import java.util.List;

import com.cleancarSMS.pojo.AreaManager;

public interface IAreaManagerService {
	public int addManager(AreaManager am);//增加区域负责人
	public int updateManager(AreaManager am);//更新区域负责人
	public int deleteManager(int id);//删除区域负责人
	public AreaManager loadById(int id);//根据id获取负责人信息
	public List<AreaManager> loadAll();//加载所有的负责人
}
