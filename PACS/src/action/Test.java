package action;
import java.util.List;

import dao.DAOUser;
import pojo.User;

public class Test {
	public static void main(String[] args){
		User doctor = new User(1,"1234","Ò½Éú");
		//Operate oper = new Operate();
		DAOUser dao = new DAOUser();
		
		//oper.insert(doctor);
		/*if(dao.delete(doctor)){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}*/
		List<User> list = dao.query(doctor);
		if(list!=null){
			for(User user:list){
				System.out.println(user.getUserType());
			}
		}
	}
}
