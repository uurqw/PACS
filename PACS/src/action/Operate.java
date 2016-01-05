package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;

import dao.DAOPatient;
import dao.DAOReport;
import dao.DAOUser;

import pojo.Doctor;
import pojo.Patient;
import pojo.Report;
import pojo.User;

public class Operate {
	
	private User user = new User();
	private Report report = new Report();
	private Patient patient = new Patient();
	private Doctor doctor = new Doctor();
	private Map<String,Object> map=null;
	private List<Object> list;
	
	/*登录*/
	public String login(){
		boolean b = false;
		boolean c = false;
		DAOUser daoUser=new DAOUser();
		User u = null;
		u = daoUser.query(user.getUserId());
		
		map = new HashMap<String,Object>();
		
		if(u!=null){
			b = user.getPassword().equals(u.getPassword());
			c = user.getUserType().equals(u.getUserType());
		}
		if(u!=null&&b&&c){
			HttpSession session = this.getRequest().getSession();
			session.setAttribute("userId", u.getUserId());
			session.setAttribute("userType", u.getUserType());
			map.put("tip", 1);
		}else{
			map.put("tip",0);
		}
		return "json";
	}
	/*获取病人基本信息*/
	public String getPaMessage(){
		HttpSession session = this.getRequest().getSession();
		DAOPatient daoPatient=new DAOPatient();
		Patient p = new Patient();
		p = daoPatient.query(patient.getId());
		map = new HashMap<String,Object>();
		if(p!=null){
			map.put("doctorId", session.getAttribute("userId"));
			map.put("patientId", p.getId());
			map.put("patientName", p.getName());
			map.put("patientAge", p.getAge());
			map.put("patientSex", p.getSex());
			map.put("patientCardId", p.getCardId());
			map.put("patientAdNum", p.getAdmissionNum());
			map.put("patientBedNum", p.getBedNum());
		}else{
			map.put("tip", 0);
		}
		return "json";
	}
	
	
	/*填写报告*/
	public String diagnosis(){
		HttpSession session = this.getRequest().getSession();
		DAOReport daoReport =new DAOReport();
		int count = daoReport.queryCount();
		report.setReportId(count+1);
		report.setDocId((Integer) session.getAttribute("userId"));
		map = new HashMap<String,Object>();
		if(daoReport.insert(report)){
			map.put("tip", 1);
			session.setAttribute("patientId", report.getPatId());
		}else{
			map.put("tip", 0);
		}
		return "json";
	}
	
	/*根据Report表主键查询报告*/
	public String queryReport(){
		DAOReport daoReport = new DAOReport();
		DAOPatient daoPatient = new DAOPatient();
		report = daoReport.query(report.getReportId());
		patient = daoPatient.query(report.getPatId());
		map.put("reportId", report.getReportId());
		map.put("patientId", report.getPatId());
		map.put("doctorId", report.getDocId());
		map.put("Name", patient.getName());
		map.put("sex", patient.getSex());
		map.put("age", patient.getAge());
		map.put("fever", report.getFever());
		map.put("vomit", report.getVomit());
		map.put("cough", report.getCough());
		map.put("diarrhea", report.getDiarrhea());
		map.put("result", report.getResult());
		return "json";
	}
	
	/*打印报告*/
	public String showReprot(){
		HttpSession session = this.getRequest().getSession();
		DAOReport daoReport = new DAOReport();
		DAOPatient daoPatient = new DAOPatient();
		map = new HashMap<String,Object>();
		if("doctor".equals((String)session.getAttribute("userType"))){
			Patient p = daoPatient.query((Integer)session.getAttribute("patientId"));
			Report r = daoReport.query(p.getId(), (Integer)session.getAttribute("userId"));
			map.put("reportId", r.getReportId());
			map.put("patientId", r.getPatId());
			map.put("doctorId", r.getDocId());
			map.put("name", p.getName());
			map.put("sex", p.getSex());
			map.put("age", p.getAge());
			map.put("fever", r.getFever());
			map.put("vomit", r.getVomit());
			map.put("cough", r.getCough());
			map.put("diarrhea", r.getDiarrhea());
			map.put("time", r.getTime());
			map.put("result", r.getResult());
		}else{
			Patient p = daoPatient.query((Integer)session.getAttribute("userId"));
			Report r = daoReport.query(p);
			map.put("reportId", r.getReportId());
			map.put("patientId", r.getPatId());
			map.put("doctorId", r.getDocId());
			map.put("name", p.getName());
			map.put("sex", p.getSex());
			map.put("age", p.getAge());
			map.put("fever", r.getFever());
			map.put("vomit", r.getVomit());
			map.put("cough", r.getCough());
			map.put("diarrhea", r.getDiarrhea());
			map.put("time", r.getTime());
			map.put("result", r.getResult());
		}
		System.out.println("json: "+map);
		return "json";
	}
	
	
	
    //获取request
  	public HttpServletRequest getRequest() {  		
  		return (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST); //struts2获取request对象的方法
  	}
  	//获取response
  	public HttpServletResponse getResponse() {
  		return (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); //struts2获取response对象的方法
  	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
  	
}
