<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>诊断报告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/info.css">
	<script src="js/jquery-1.10.1.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="wrap">
		<div class="time-info">
			<label>就诊时间：</label>
			<span></span>
		</div>
		<div class="title-info">
			<ul>
		 		<li>报告编号：<span></span></li>
			 	<li>病人编号：<span></span></li>
			 	<li>医生工号：<span></span></li>
			 </ul>
		</div>

		<div class="patient-info">
			<ul>
			 	<li>姓名：<span></span></li>
			 	<li>性别：<span></span></li>
		 		<li>年龄：<span></span></li>
		 	</ul>
		</div>

		<div class="illness-info">
			<ul>
				<li>是否发热：<span></span></li>
				<li>是否呕吐：<span></span></li>
				<li>是否咳嗽：<span></span></li>
				<li>是否腹泻：<span></span></li>
			</ul>
		</div>

		<div class="extra-info">
			<label>诊断结果：</label>
			<div class="extra-txt"></div>
		</div>

		<div class="btn-info">
			<a href="<%=basePath%>Consultation.action">返&nbsp;回</a>
			<a href="javascript:(0)">打&nbsp;印</a>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$.ajax({
			async: false,
            url:"Operate_showReprot.action",
            dataType:'json',
            type: "get",
            success: function (data) {
                	$(".title-info span:eq(0)").html(001);//报告编号写上后台的id;
               		$(".title-info span:eq(1)").html(data.patientId);
               		$(".title-info span:eq(2)").html(data.doctorId);
               		$(".patient-info span:eq(0)").html(data.name);
               		$(".patient-info span:eq(1)").html(data.sex);
               		$(".patient-info span:eq(2)").html(data.age);
               		$(".time-info span").html(data.time);
               		alert(data.time);
               		$(".extra-info").html(data.result);
               		if(data.fever==true){
               			$(".illness-info span:eq(0)").html("是");
               		}else{
               			$(".illness-info span:eq(0)").html("否");
               		}
               		if(data.vomit==true){
               			$(".illness-info span:eq(1)").html("是");
               		}else{
               			$(".illness-info span:eq(1)").html("否");
               		}
               		if(data.cough==true){
               			$(".illness-info span:eq(2)").html("是");
               		}else{
               			$(".illness-info span:eq(2)").html("否");
               		}
               		if(data.diarrhea==true){
               			$(".illness-info span:eq(3)").html("是");
               		}else{
               			$(".illness-info span:eq(3)").html("否");
               		}
            }
		});
		
	});
	</script>
  </body>
</html>
