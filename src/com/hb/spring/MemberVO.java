package com.hb.spring;

import java.util.Date;

public class MemberVO {
	private long id;
	private String email;
	private String password;
	private String name;
	private Date registerDate;
	
	public MemberVO(String email,String password,String name,Date registerDate) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	public void changePassword(String oldPassword,String newPassword){
		if(!password.equals(oldPassword)){
			/*에러처리*/
			throw new IdPasswordNotMatchingException();
		}
		this.password = newPassword;
	}
	
}
