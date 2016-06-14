package com.hb.spring;

public class ChangePasswordService {
	private MemberDAO dao;
	
	public ChangePasswordService() {
		
	}
	public ChangePasswordService(MemberDAO dao){
		this.dao = dao;
	}
	public void changePassword(String email,String oldPwd,String newPwd){
		MemberVO vo = dao.selectByEmail(email);
		if(vo == null){
			throw new MemberNotFoundException();
		}
		vo.changePassword(oldPwd, newPwd);
		dao.update(vo);
	}
}
