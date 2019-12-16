package com.yinhe.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yinhe.bean.Orderitem;
import com.yinhe.bean.Orders;
import com.yinhe.utils.DataSourceUtils;

public class OrderDao {

	//��Ӷ���
	public void addOrder(Orders order) throws SQLException{
		  QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		  String sql="insert into orders values(?,?,?,?,?,?,?,?)";
		  runner.update(sql, order.getOid(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrdertime()),order.getTotal(),
				  order.getState(),order.getAddress(),order.getName(),
				  order.getTelephone(),order.getUid());
	  }
	
	//��Ӷ�����
	public void addOrderItem(Orderitem orderItem) throws SQLException{
		  QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		  String sql="insert into orderitem values(?,?,?,?,?)";
		  runner.update(sql, orderItem.getItemid(),orderItem.getCount(),
				  orderItem.getSubtotal(),
				  orderItem.getPid(),
				  orderItem.getOid());
	}
	//���¶���
	public void updateOrder(Orders order) throws SQLException{
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update orders set address=?,name=?,telephone=?,state=? where oid=?";
		runner.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getState(),order.getOid());
	}
	//���¶���
	public void updateOrderStateByOid(String oid) throws SQLException{
			QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="update orders set state=? where oid=?";
			runner.update(sql,1,oid);
	}
	//��ѯ�û��Ķ���
   public List<Orders> findOrderByUid(String uid,int start,int pageSize) throws SQLException{
	   QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	   String sql="select * from orders where uid=? limit ? ,? ";
	   return runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid,start,pageSize);
   }
   public int findOrderCountByUid(String uid)throws SQLException{
	   QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	   String sql="select count(*) from orders where uid=?";
	   Long totalCount=(Long)runner.query(sql,new ScalarHandler(), uid);
	   return totalCount.intValue();
   }
   //ͨ���������  �������ж�����
   public List<Map<String,Object>> findAllOrderItemByOid(String oid) throws SQLException{
	   QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	   String sql="select p.pname,p.shop_price,p.pimage ,o.count,o.subtotal  from  orderitem o,product p where o.pid=p.pid and o.oid=?";
	  return  runner.query(sql, new MapListHandler(), oid);
   }
   
 //��ѯ���еĶ��� ���ҷ�ҳ
   public List<Orders> findAllOrder(int start,int pageSize) throws SQLException{
	   QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	   String sql="select * from orders  limit ? ,? ";
	   return runner.query(sql, new BeanListHandler<Orders>(Orders.class),start,pageSize);
   }
   public int findOrderCount()throws SQLException{
	   QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	   String sql="select count(*) from orders ";
	   Long totalCount=(Long)runner.query(sql,new ScalarHandler());
	   return totalCount.intValue();
   }
   
 //ɾ�������м��
 	public void deleteOrderItem(String oid) throws SQLException{
 			QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
 			String sql="delete from orderitem where oid=?";
 			runner.update(sql,oid);
 	}
   
 	//ɾ������
 	 	public void deleteOrder(String oid) throws SQLException{
 	 			QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
 	 			String sql="delete from orders  where oid=?";
 	 			runner.update(sql,oid);
 	 	}
   
   
}
