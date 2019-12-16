package com.yinhe.service;

import java.sql.SQLException;
import java.util.List;

import com.yinhe.bean.Category;
import com.yinhe.dao.CategoryDao;

public class CategoryService {

	private CategoryDao categoryDao=new CategoryDao();
	
	//������������
	public List<Category> findAllCategory(){
		try {
			return categoryDao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//ͨ�����ͱ�� �������Ͷ���
	public Category findCategoryByCid(String cid){
		try {
			return categoryDao.findCategoryByCid(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//�������
	public void addCategory(String id,String cname){
		try {
			categoryDao.addCategory(id, cname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ɾ��
	public void deleteCategory(String id) {
		try {
			categoryDao.deleteCategory(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�޸�
	public void updateCategory(String id, String cname) {
		try {
			categoryDao.updateCategory(id, cname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
