package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.Patient;
import pojo.Report;

public class DAOReport {
	//在Hibernate中，所有的操作都是通过Session完成
    //此Session不同于JSP的Session
	private Session session = null;
	 //在构造方法之中实例化session对象
	public DAOReport(){
		// 找到Hibernate配置
		Configuration config = new Configuration().configure();
		//从配置中取出SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		//从SessionFactory中取出一个Session
		session = factory.openSession();
	}
	//所有操作都是由session进行的
	
	//向数据库中增加数据
	public boolean insert(Report report){
		try{//开始事务
			Transaction tran = this.session.beginTransaction();
			//执行语句
			this.session.save(report);
			//提交事务
			tran.commit();
			//执行成功
			return true;
		}catch(RuntimeException e){
			//执行失败
			System.out.println(""+e);
			return false;
		}finally{
			//关闭session
			this.session.close();
		}
	}
	
	//向数据库中删除数据
	public boolean delete(Report report){
		try{//开始事务
			Transaction tran = this.session.beginTransaction();
			//执行语句
			this.session.delete(report);
			//提交事务
			tran.commit();
			//删除成功
			return true;
		}catch(RuntimeException e){
			//删除失败
			return false;
		}finally{
			//关闭session
			this.session.close();
		}
	}
	
	//向数据库中修改数据
	public boolean update(Report report){
		if(report!=null){
			try{//开始事务
				Transaction tran = this.session.beginTransaction();
				//执行语句
				this.session.update(report);
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
		public Report query(int id){
			Report report = null;
			report = (Report) this.session.get(Report.class, id);
			return report;
		}
	
	//根据病人Id和医生Id查询
	@SuppressWarnings("unchecked")
	public Report query(int patId,int docId){
		List<Report> list = null;
		Report r = null;
		String hql="from Report as report where report.patId="+patId+" and report.docId="+docId+" order by report.reportId desc";
		list = this.session.createQuery(hql).list();
		r = list.get(0);
		return r;
	}
	
	//根据病人Id查询最近的诊断报告
	@SuppressWarnings("unchecked")
	public Report query(Patient patient){
		Report report = null;
		List<Report> list = null;
		String hql="from Report as report where report.patId="+patient.getId()+" order by report.reportId desc";
		list = this.session.createQuery(hql).list();
		report = list.get(0);
		return report;
	}
	
	//查询Report中总记录数
	public int queryCount(){
		int count = ((Long) this.session.createQuery("select count(*) from Report").uniqueResult()).intValue();
		return count;
	}
	
}
