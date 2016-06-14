package com.hb.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
	private static long nextId = 0;
	private Map<String,MemberVO> map = new HashMap<>();
	
	public MemberVO selectByEmail(String email){
		return map.get(email);
	}
	
	public void insert(MemberVO vo){
		vo.setId(++nextId);
		map.put(vo.getEmail(), vo);
	}
	
	public void update(MemberVO vo){
		map.put(vo.getEmail(), vo);
	}
	public Collection<MemberVO> selectAll(){
		return map.values();
	}
	
}
