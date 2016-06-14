package com.hb.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.hb.assembler.Assembler;
import com.hb.spring.ChangePasswordService;
import com.hb.spring.IdPasswordNotMatchingException;
import com.hb.spring.MemberNotFoundException;
import com.hb.spring.MemberRegisterService;
import com.hb.spring.RegisterRequest;

public class MainForAssembler {
	
	private static Assembler asm = new Assembler();
	
	public static void main(String[] args) throws IOException {
		/*Scanner scan = new Scanner(System.in);*/
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("명령어를 입력하세요");
			/*String command = scan.nextLine();*/
			String command = reader.readLine();
			if(command.equals("exit")){
				System.out.println("exit!!!");
			}
			if(command.startsWith("new ")){
				//regist
				processNewCommand(command.split(" "));
				continue;
			}else if(command.startsWith("change ")){
				//changePassword 
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}
	private static void processNewCommand(String[] arg){
		if(arg.length != 5){
			printHelp();
			return;
		}
		MemberRegisterService regSvc = asm.getRegSvc();
		RegisterRequest reg = new RegisterRequest();
		reg.setEmail(arg[1]);
		reg.setName(arg[2]);
		reg.setPassword(arg[3]);
		reg.setConfirmPassword(arg[4]);
		
		if(!reg.isPasswordEqualToConfirmPassword()){
			System.out.println("password not matching!!!");
			return;
		}
		try {
			regSvc.regist(reg);
			System.out.println("Input Success");
		} catch (Exception e) {
			System.out.println("Input warning ");
		}
	}
	
	private static void processChangeCommand(String[] arg){
		if(arg.length != 4){
			printHelp();
			return;
		}
		ChangePasswordService pwdSvc = asm.getPwdScv();
		try {
			pwdSvc.changePassword(arg[1], arg[2], arg[3]);
		} catch (MemberNotFoundException e) {
			System.out.println("not Exist email");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("not Matching email Password");
		}
	}
	
	private static void printHelp(){
		System.out.println();
		System.out.println("잘못된 명령 입니다. 아래 명령어 사용법 확인 바람.");
		System.out.println("명령어 사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}
}
