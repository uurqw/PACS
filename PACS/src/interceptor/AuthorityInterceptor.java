package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例  
		Map<String,Object> session = invocation.getInvocationContext().getSession();
		String name = invocation.getInvocationContext().getName();
		
		if(name.equals("Operate_login")){
			return invocation.invoke();
		}
        if(session.get("userId") == null){
        	return "login";
        }else{
        	int userId = 0;
        	userId = (Integer) session.get("userId");
        	if(userId==0){
        		return "login";
        	}else{
        		return invocation.invoke();
        	}
        }
	}
	
}
