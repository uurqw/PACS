package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pojo.Doctor;

public class DAODoctor {
	//��Hibernate�У����еĲ�������ͨ��Session���
    //��Session��ͬ��JSP��Session
	private Session session = null;
	 //�ڹ��췽��֮��ʵ����session����
	public DAODoctor(){
		// �ҵ�Hibernate����
		Configuration config = new Configuration().configure();
		//��������ȡ��SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		//��SessionFactory��ȡ��һ��Session
		session = factory.openSession();
	}
	//���в���������session���е�
	
	//�����ݿ�����������
	public boolean insert(Doctor doctor){
		try{//��ʼ����
			Transaction tran = this.session.beginTransaction();
			//ִ�����
			this.session.save(doctor);
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
	public boolean delete(Doctor doctor){
		try{//��ʼ����
			Transaction tran = this.session.beginTransaction();
			//ִ�����
			this.session.delete(doctor);
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
	
	//�����ݿ����޸�����
	public boolean update(Doctor doctor){
		if(doctor!=null){
			try{//��ʼ����
				Transaction tran = this.session.beginTransaction();
				//ִ�����
				this.session.update(doctor);
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
	public Doctor query(int id){
		Doctor doctor = null;
		doctor = (Doctor) this.session.get(Doctor.class, id);
		return doctor;
	}
	
}
