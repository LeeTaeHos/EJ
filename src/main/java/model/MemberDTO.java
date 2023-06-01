package model;

import java.sql.Timestamp;

public class MemberDTO {
 private String id;
 private String pw;
 private String name;
 private String tel;
 private String email;
 private String addr;
 private Timestamp rdate;
 
 public MemberDTO() {}

public MemberDTO(String id, String pw, String name, String tel, String email, String addr, Timestamp rdate) {
	super();
	this.id = id;
	this.pw = pw;
	this.name = name;
	this.tel = tel;
	this.email = email;
	this.addr = addr;
	this.rdate = rdate;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPw() {
	return pw;
}

public void setPw(String pw) {
	this.pw = pw;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddr() {
	return addr;
}

public void setAddr(String addr) {
	this.addr = addr;
}

public Timestamp getRdate() {
	return rdate;
}

public void setRdate(Timestamp rdate) {
	this.rdate = rdate;
}
 
 
 
}
