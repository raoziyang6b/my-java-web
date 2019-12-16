package com.yinhe.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.yinhe.bean.Product;
import com.yinhe.dao.ProductDao;
import com.yinhe.utils.CommonsUtils;

public class ProductService {

	private  ProductDao productDao=new ProductDao();
	 
	 //������������Ʒ
	 public List<Product> findHostProductList(){

		 try {
			return productDao.findHostProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 //������Ʒ
	 public List<Product> findNewProductList(){
	
		 try {
			return productDao.findNewProductList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 //ͨ�����ͱ�Ų�ѯ��Ʒ��Ϣ
	 public List<Product> findProductByCategory(String cid,int start,int pageSize){
		 try {
			return productDao.findProductByCategory(cid, start, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 //ͨ����Ʒ���� ��ѯ��Ʒ����
	 public int getCount(String cid){
		 try {
		  return	productDao.getCount(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return 0;
	 }
	 //ͨ��pid������Ʒ��Ϣ
	 public Product findProductByPid(String pid) {
		 try {
			return productDao.findProductByPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 
	 public List<Product> findProduct(int start,int pageSize){
		 try {
			return productDao.findProduct(start, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 
	 public int getTotalCount() {
		 try {
			return productDao.getTotalCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return 0;
	 }
	 
	 public void addProduct(Product product){
		 try {
			 product.setPid(CommonsUtils.getUUID());
			 product.setPdate(new Date());
			productDao.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void deleteProduct(String pid){
		 try {
			productDao.deleteProduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 //���²�Ʒ
		public void updateProduct(Product product){
			try {
				productDao.updateProduct(product);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
