package com.cleancarSMS.dao;

import java.util.List;

import com.cleancarSMS.pojo.AreaManager;

public interface IAreaManagerDao {
	
	public int add(AreaManager am);//添加
	public int update(AreaManager am);//更新
	public int delete(int id);//删除
	public AreaManager loadById(int id);//根据id获取负责人信息
	public List<AreaManager> loadByProvince(String province);//根据省份获取列表
	public List<AreaManager> loadAll();//获取所有
}
