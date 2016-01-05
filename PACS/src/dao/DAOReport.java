package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.Patient;
import pojo.Report;

public class DAOReport {
	//��Hibernate�У����еĲ�������ͨ��Session���
    //��Session��ͬ��JSP��Session
	private Session session = null;
	 //�ڹ��췽��֮��ʵ����session����
	public DAOReport(){
		// �ҵ�Hibernate����
		Configuration config = new Configuration().configure();
		//��������ȡ��SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		//��SessionFactory��ȡ��һ��Session
		session = factory.openSession();
	}
	//���в���������session���е�
	
	//�����ݿ�����������
	public boolean insert(Report report){
		try{//��ʼ����
			Transaction tran = this.session.beginTransaction();
			//ִ�����
			this.session.save(report);
			//�ύ����
			tran.commit();
			//ִ�гɹ�
			return true;
		}catch(RuntimeException e){
			//ִ��ʧ��
			System.out.println(""+e);
			return false;
		}finally{
			//�ر�session
			this.session.close();
		}
	}
	
	//�����ݿ���ɾ������
	public boolean delete(Report report){
		try{//��ʼ����
			Transaction tran = this.session.beginTransaction();
			//ִ�����
			this.session.delete(report);
			//�ύ����
			tran.commit();
			//ɾ���ɹ�
			return true;
		}catch(RuntimeException e){
			//ɾ��ʧ��
			return false;
		}finally{
			//�ر�session
			this.session.close();
		}
	}
	
	//�����ݿ����޸�����
	public boolean update(Report report){
		if(report!=null){
			try{//��ʼ����
				Transaction tran = this.session.beginTransaction();
				//ִ�����
				this.session.update(report);
				//�ύ����
				tran.commit();
				//ִ�гɹ�
				return true;
			}catch(RuntimeException e){
				//ִ��ʧ��
				return false;
			}finally{
				//�ر�session
				this.session.close();
			}
		}else{
			return false;
		}
	}
	
	//�����ݿ��в�ѯ����
		public Report query(int id){
			Report report = null;
			report = (Report) this.session.get(Report.class, id);
			return report;
		}
	
	//���ݲ���Id��ҽ��Id��ѯ
	@SuppressWarnings("unchecked")
	public Report query(int patId,int docId){
		List<Report> list = null;
		Report r = null;
		String hql="from Report as report where report.patId="+patId+" and report.docId="+docId+" order by report.reportId desc";
		list = this.session.createQuery(hql).list();
		r = list.get(0);
		return r;
	}
	
	//���ݲ���Id��ѯ�������ϱ���
	@SuppressWarnings("unchecked")
	public Report query(Patient patient){
		Report report = null;
		List<Report> list = null;
		String hql="from Report as report where report.patId="+patient.getId()+" order by report.reportId desc";
		list = this.session.createQuery(hql).list();
		report = list.get(0);
		return report;
	}
	
	//��ѯReport���ܼ�¼��
	public int queryCount(){
		int count = ((Long) this.session.createQuery("select count(*) from Report").uniqueResult()).intValue();
		return count;
	}
	
}
