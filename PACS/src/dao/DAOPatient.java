package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pojo.Patient;

public class DAOPatient {
	//��Hibernate�У����еĲ�������ͨ��Session���
    //��Session��ͬ��JSP��Session
	private Session session = null;
	 //�ڹ��췽��֮��ʵ����session����
	public DAOPatient(){
		// �ҵ�Hibernate����
		Configuration config = new Configuration().configure();
		//��������ȡ��SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		//��SessionFactory��ȡ��һ��Session
		session = factory.openSession();
	}
	//���в���������session���е�
	
	//�����ݿ�����������
	public boolean insert(Patient patient){
		try{//��ʼ����
			Transaction tran = this.session.beginTransaction();
			//ִ�����
			this.session.save(patient);
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
	}
	
	//�����ݿ���ɾ������
	public boolean delete(Patient patient){
		try{//��ʼ����
			Transaction tran = this.session.beginTransaction();
			//ִ�����
			this.session.delete(patient);
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
	public boolean update(Patient patient){
		if(patient!=null){
			try{//��ʼ����
				Transaction tran = this.session.beginTransaction();
				//ִ�����
				this.session.update(patient);
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
	public Patient query(int id){
		Patient patient = null;
		patient = (Patient) this.session.get(Patient.class, id);
		return patient;
	}
	
}
