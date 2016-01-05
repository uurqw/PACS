package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pojo.Doctor;

public class DAODoctor {
	//在Hibernate中，所有的操作都是通过Session完成
    //此Session不同于JSP的Session
	private Session session = null;
	 //在构造方法之中实例化session对象
	public DAODoctor(){
		// 找到Hibernate配置
		Configuration config = new Configuration().configure();
		//从配置中取出SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		//从SessionFactory中取出一个Session
		session = factory.openSession();
	}
	//所有操作都是由session进行的
	
	//向数据库中增加数据
	public boolean insert(Doctor doctor){
		try{//开始事务
			Transaction tran = this.session.beginTransaction();
			//执行语句
			this.session.save(doctor);
			//提交事务
			tran.commit();
			//执行成功
			return true;
		}catch(RuntimeException e){
			//执行失败
			return false;
		}finally{
			//关闭session
			this.session.close();
		}
	}
	
	//向数据库中删除数据
	public boolean delete(Doctor doctor){
		try{//开始事务
			Transaction tran = this.session.beginTransaction();
			//执行语句
			this.session.delete(doctor);
			//提交事务
			tran.commit();
			//执行成功
			return true;
		}catch(RuntimeException e){
			//执行失败
			return false;
		}finally{
			//关闭session
			this.session.close();
		}
	}
	
	//向数据库中修改数据
	public boolean update(Doctor doctor){
		if(doctor!=null){
			try{//开始事务
				Transaction tran = this.session.beginTransaction();
				//执行语句
				this.session.update(doctor);
				//提交事务
				tran.commit();
				//执行成功
				return true;
			}catch(RuntimeException e){
				//执行失败
				return false;
			}finally{
				//关闭session
				this.session.close();
			}
		}else{
			return false;
		}
	}
	
	//向数据库中查询数据
	public Doctor query(int id){
		Doctor doctor = null;
		doctor = (Doctor) this.session.get(Doctor.class, id);
		return doctor;
	}
	
}
