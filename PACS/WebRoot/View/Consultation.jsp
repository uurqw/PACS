<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Consultation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/doctor.css">
	<script src="js/jquery-1.10.1.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div class="wrap">
		<div class="title">
			<h1>诊断报告</h1>
			<span class="cur-time"></span>
		</div>
		<div class="patient-id">
			<label for="p-id">请输入就诊患者编号</label>
			<input type="text"><br><br>
			<p id="errorMes"></p>
			<button id="submit">提&nbsp;&nbsp;交</button>
		</div>
		<div class="form-wrap" id="form-wrap">
		 	<section class="form-info">
			 	<ul>
		 			<li>报告编号：<span></span></li>
			 		<li>病人编号：<span></span></li>
			 		<li>医生工号：<span></span></li>
			 	</ul>
			 </section>
			 <section class="patient-info">
			 	<ul>
			 		<li>姓名：<span></span></li>
			 		<li>性别：<span></span></li>
			 		<li>年龄：<span></span></li>
			 	</ul>
			 </section>
			 <form>
			 <section class="illness-info">
			 	<ul>
			 		<li>
			 			<div id="fever">
			 				<span>是否发热：</span>
			 				<input type="radio" id="fever-1" name="report.fever" value="true">
			 				<label for="fever-1">是</label>
			 				<input type="radio" id="fever-2" name="report.fever" value="false">
			 				<label for="fever-2">否</label>
			 			</div>
			 		</li>
			 		<li>
			 			<div id="vomit">
			 				<span>是否呕吐：</span>
			 				<input type="radio" id="vomit-1" name="report.vomit" value="true">
			 				<label for="vomit-1">是</label>
			 				<input type="radio" id="vomit-2" name="report.vomit" value="false">
			 				<label for="vomit-2">否</label>
			 			</div>
			 		</li>
			 		<li>
			 			<div id="cough">
			 				<span>是否咳嗽：</span>
			 				<input type="radio" id="cough-1" name="report.cough" value="true">
			 				<label for="cough-1">是</label>
			 				<input type="radio" id="cough-2" name="report.cough" value="false">
			 				<label for="cough-2">否</label>
			 			</div>
			 		</li>
			 		<li>
			 			<div id="stomach">
			 				<span>是否腹泻：</span>
			 				<input type="radio" id="stomach-1" name="report.diarrhea" value="true">
			 				<label for="stomach-1">是</label>
			 				<input type="radio" id="stomach-2" name="report.diarrhea" value="false">
			 				<label for="stomach-2">否</label>
			 			</div>
			 		</li>
			 	</ul>
			 </section>

			 <section class="extra-info">
			 	<p>诊断结果：</p>
			 	<textarea name="report.result"></textarea>
			 </section>
			 </form>

			 <section class="btn-info">
			 	<button id="submit" style="border:none;">提交诊断报告</button>
			 </section>
			<script>
				var time=new Date();
				var month=time.getMonth()+1;
				var timeNow=time.getFullYear()+"-"+month+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
				
				$(".cur-time").html(timeNow);

				$(function () {	
					$(".btn-info").click(function(){
						var params = $("form").serialize();
						params = decodeURIComponent(params,true);
						$.ajax({
							url:"Operate_diagnosis.action",
							data:params+'&report.time='+$(".cur-time").html()+'&report.patId='+$(".form-info span:eq(1)").html(),
							dataType:'json',
							type:"post",
							success:function(data){
								if(data.tip==1){
									window.location.href="/PACS/ReportView.action";
								}else{
									alert("提交失败,请联系系统管理员");
								}
							}
						});
					});
					$(".patient-id button").click(function(){
						if($(".patient-id input").val()!=""){
							$.ajax({
								async:false,
		                        url:"Operate_getPaMessage.action",
		                        data: 'patient.id='+$(".patient-id input").val(),
		                        dataType:'json',
		                        type: "post",
		                        success: function (data) {
		                        	if(data.tip==0){
		                        		$("#errorMes").text("该编号不存在");
		                        	}
		                        	else{
		                        		$(".patient-id").css("display","none");
		                        		$("#form-wrap").css("display","block");
		                        		$(".form-info span:eq(0)").html(001);//报告编号写上后台的id;
		                        		$(".form-info span:eq(1)").html(data.patientId);
		                        		$(".form-info span:eq(2)").html(data.doctorId);
		                        		$(".patient-info span:eq(0)").html(data.patientName);
		                        		$(".patient-info span:eq(1)").html(data.patientSex);
		                        		$(".patient-info span:eq(2)").html(data.patientAge);
		                        	}
		                        }
		                    });
						}
						else{
						$(".patient-id input").css("border","red solid 1px");
						}
					});
							
				});
			</script>
		</div>
	</div>
  </body>
</html>
