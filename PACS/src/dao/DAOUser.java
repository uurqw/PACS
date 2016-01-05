package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pojo.User;

public class DAOUser {
	//锟斤拷Hibernate锟叫ｏ拷锟斤拷锟叫的诧拷锟斤拷锟斤拷锟斤拷通锟斤拷Session锟斤拷锟�
    //锟斤拷Session锟斤拷同锟斤拷JSP锟斤拷Session
	private Session session = null;
	 //锟节癸拷锟届方锟斤拷之锟斤拷实锟斤拷session锟斤拷锟斤拷
	public DAOUser(){
		// 锟揭碉拷Hibernate锟斤拷锟斤拷
		Configuration config = new Configuration().configure();
		//锟斤拷锟斤拷锟斤拷锟斤拷取锟斤拷SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		//锟斤拷SessionFactory锟斤拷取锟斤拷一锟斤拷Session
		session = factory.openSession();
	}
	//锟斤拷锟叫诧拷锟斤拷锟斤拷锟斤拷锟斤拷session锟斤拷锟叫碉拷
	
	//锟斤拷锟斤拷菘锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	public boolean insert(User user){
		try{//锟斤拷始锟斤拷锟斤拷
			Transaction tran = this.session.beginTransaction();
			//执锟斤拷锟斤拷锟�
			this.session.save(user);
			//锟结交锟斤拷锟斤拷
			tran.commit();
			//执锟叫成癸拷
			return true;
		}catch(RuntimeException e){
			//执锟斤拷失锟斤拷
			return false;
		}finally{
			//锟截憋拷session
			this.session.close();
		}
	}
	
	//锟斤拷锟斤拷菘锟斤拷锟缴撅拷锟斤拷锟斤拷
	public boolean delete(User user){
		try{//锟斤拷始锟斤拷锟斤拷
			Transaction tran = this.session.beginTransaction();
			//执锟斤拷锟斤拷锟�
			this.session.delete(user);
			//锟结交锟斤拷锟斤拷
			tran.commit();
			//删锟斤拷晒锟�
			return true;
		}catch(RuntimeException e){
			//删锟斤拷失锟斤拷
			return false;
		}finally{
			//锟截憋拷session
			this.session.close();
		}
	}
	
	//锟斤拷锟斤拷菘锟斤拷锟斤拷薷锟斤拷锟斤拷
	public boolean update(User user){
		if(user!=null){
			try{//锟斤拷始锟斤拷锟斤拷
				Transaction tran = this.session.beginTransaction();
				//执锟斤拷锟斤拷锟�
				this.session.update(user);
				//锟结交锟斤拷锟斤拷
				tran.commit();
				//执锟叫成癸拷
				return true;
			}catch(RuntimeException e){
				//执锟斤拷失锟斤拷
				return false;
			}finally{
				//锟截憋拷session
				this.session.close();
			}
		}else{
			return false;
		}
	}
	
	//锟斤拷锟斤拷菘锟斤拷胁锟窖拷锟斤拷
	public User query(int id){
		User user = null;
		user = (User) this.session.get(User.class, id);
		return user;
	}
	
}
