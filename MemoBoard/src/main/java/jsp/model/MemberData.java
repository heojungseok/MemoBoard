package jsp.model;

import java.util.Date;

public class MemberData {
	//private 묶었기에 getter setter 만들어야함
	private int midx;
	private String userid;
	private String passwd;
	private String email;
	private String username;
	private String address;
	private String age;
	private int stat;
	private Date wdate;

	//memberInfo 를 위한 
	public MemberData(int midx, String userid, String passwd, String email, String username, String address,String age,int stat,
			Date wdate) {
		
		this.midx = midx;
		this.userid = userid;
		this.passwd = passwd;
		this.email = email;
		this.username = username;
		this.address = address;
		this.age = age;
		this.stat = stat;
		this.wdate = wdate;
	}
	//login 을 위한
	public MemberData(int midx, String userid, String passwd) {
	
		this.midx = midx;
		this.userid = userid;
		this.passwd = passwd;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	
	

}
