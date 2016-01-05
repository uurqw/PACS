<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>临床工作站会诊系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/index.css">
	<script src="js/jquery-1.10.1.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="wrap">
	</div>

	<div class="login-wrap">
		<h1>登&nbsp;&nbsp;录</h1>
		<div id="msForm">
			<div class="text-wrap">
				<label>用户号</label>
				<input type="text"name="" placeholder="请输入用户号"><span></span>
				<label>密码</label>
				<input type="password" placeholder="请输入密码"><span></span>
				<p id="info"></p>
			</div>


			<div class="btn-wrap">
				<select>
					<option>doctor</option>
					<option>patient</option>
				</select>
				<button class="sub-btn" id="login" style="border:none;">登&nbsp;&nbsp;录</button>
				
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			var name=$("input[type=text]");
			var psw=$("input[type=password]");
			var login=$("#login");
			
			/*if("就诊医师"==$("select :selected").val()){
				select = "doctor";
			}else{
				select = "patient";
			}*/
			psw[0].disabled=true;
			login[0].disabled=true;
			name.blur(function(){
				if(name.val().length<6){
			 		$("#info").text("用户名长度不能少于6位");
			 		name.addClass("change");
			 		psw[0].disabled=true;
			 	}
			 	else{
			 		name.removeClass("change");
			 		psw[0].disabled=false;
			 	}
			});
			/*psw.blur(function(){
				if(psw.val().length<6){
			 			$("#info").text("密码长度不能少于6位");
			 		psw.addClass("change");
			 	}
			 	else{
			 		login[0].disabled=false;
			 	}
			});*/
			login[0].disabled=false;
			$("#login").click(function(){
				var select=$("select :selected").val();
				if(name.val()==""&&psw.val()==""){
					name.addClass("change");
					psw.addClass("change");
					$("#info").text("请输入用户名与密码");
				}
				else{
					$.ajax({
						async: false,
	                    url:"Operate_login.action",
	                    dataType:'json',
	                    data: 'user.userId='+$("input[type=text]").val()+'&user.password='+$("input[type=password]").val()+'&user.userType='+$("select :selected").val(),
	                    type: "post",
	                    success: function (data) {
	                        if(data.tip==1){
	                        	if(select=="doctor"){
	                        		window.location.href="/PACS/Consultation.action";
	                        	}else if(select=="patient"){
	                        		window.location.href="/PACS/ReportView.action";
	                        	}
	                        }else{
	                        	alert("该类型用户名或密码不正确");
	                        }
	                    }
	                });
				}
			});
		});
	</script>
  </body>
</html>
