package com.hb.spring;

import java.util.Date;

public class MemberRegisterService {
	private MemberDAO dao;
	public MemberRegisterService(MemberDAO dao){
		this.dao = dao;
	}
	public void regist(/*받는인자*/RegisterRequest req){
		/*정보확인*/
		MemberVO vo = dao.selectByEmail(req.getEmail());
		/*맞으면 넣고 틀리면 Exception*/
		if(vo!=null){
			throw new AlreadyExistingMemberException("email : "+req.getEmail());
		}
		MemberVO newvo = new MemberVO(req.getEmail(),req.getPassword(),req.getName(),new Date());
		dao.insert(newvo);
	}
}
