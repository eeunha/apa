package com.apa.model;

public class HospitalInfoDTO {
	//h.hospitalid , h.hospitalname, h.hospitaladdress, h.hospitalemail, h.hospitaltel, h.ishospital,
    //d.departmentname, rt.opentime as breakopen, rt.closetime as breakclose, ot.opentime, ot.closetime,
    //ho.face, ho.unface, ho.housecall, ho.ishealthcheck, ho.vaccination, ho.ishospitalnightcare, ho.ishospitalholiday
	private String id;
	private String name;
	private String address;
	private String email;
	private String tel;
	private String open;
	private String deptname;
	private String breakopen;
	private String breakclose;
	private String opentime;
	private String Closetime;
	private String face;
	private String unface;
	private String call;
	private String check;
	private String vaccin;
	private String night;
	private String holiday;
	
	private String deptnames;
	private String seq;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getDeptnames() {
		return deptnames;
	}
	public void setDeptnames(String deptnames) {
		this.deptnames = deptnames;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getBreakopen() {
		return breakopen;
	}
	public void setBreakopen(String breakopen) {
		this.breakopen = breakopen;
	}
	public String getBreakclose() {
		return breakclose;
	}
	public void setBreakclose(String breakclose) {
		this.breakclose = breakclose;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getClosetime() {
		return Closetime;
	}
	public void setClosetime(String Closetime) {
		this.Closetime = Closetime;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getUnface() {
		return unface;
	}
	public void setUnface(String unface) {
		this.unface = unface;
	}
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	public String getVaccin() {
		return vaccin;
	}
	public void setVaccin(String vaccin) {
		this.vaccin = vaccin;
	}
	public String getNight() {
		return night;
	}
	public void setNight(String night) {
		this.night = night;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	
}
