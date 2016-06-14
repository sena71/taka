package com.hb.spring;

public class MemberInfoPrinter {
	private MemberDAO dao;
	private MemberPrinter printer;
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String email){
		MemberVO vo = dao.selectByEmail(email);
		if(vo == null){
			System.out.println("데이터가 없습니다");
			return;
		}
		printer.print(vo);
		System.out.println();
	}
}
