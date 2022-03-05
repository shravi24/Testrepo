package com.registration;

public class Register {
	
	private int id;
    private String username, password;
    private String emailid;
    private long phone_no;
    private String connpassword;
    
	public int getId() { //getter
		return id;
	}
	public void setId(int id) {  //setter
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public long getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}
	public String getConnpassword() {
		return connpassword;
	}
	public void setConnpassword(String connpassword) {
		this.connpassword = connpassword;
	}
	@Override
	public String toString() {
//		return "Register [id=" + id + ", username=" + username + ", password=" + password + ", emailid=" + emailid
//				+ ", phone_no=" + phone_no + ", connpassword=" + connpassword + "]";
		
		
		return  id +"	" + username + "	"+ emailid+"		"+phone_no;
				
	}
	
}//class
