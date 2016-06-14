package com.hb.spring;

public class MemberPrinter {
	public void print(MemberVO vo){
		System.out.println("회원정보 : 아이디 = "+vo.getId()+
							", 이메일 = "+vo.getEmail()+
							", 이름 = "+vo.getName()+
							", 등록일 = "+vo.getRegisterDate());
	}
}
