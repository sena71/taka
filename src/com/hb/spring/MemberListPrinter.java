package com.hb.spring;

import java.util.Collection;

public class MemberListPrinter {
	private MemberDAO dao;
	private MemberPrinter printer;
	public MemberListPrinter() {
		
	}
	public MemberListPrinter(MemberDAO dao,MemberPrinter printer){
		this.dao = dao;
		this.printer = printer;
	}
	public void printAll(){
		Collection<MemberVO> members = dao.selectAll();
		for (MemberVO vo : members) {
			printer.print(vo);
		}
	}
}
