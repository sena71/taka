package com.hb.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hb.spring.ChangePasswordService;
import com.hb.spring.IdPasswordNotMatchingException;
import com.hb.spring.MemberInfoPrinter;
import com.hb.spring.MemberListPrinter;
import com.hb.spring.MemberNotFoundException;
import com.hb.spring.MemberRegisterService;
import com.hb.spring.RegisterRequest;
import com.hb.spring.VersionPrinter;

public class MainForSpring {

	/*private static Assembler asm = new Assembler();*/
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		/*Scanner scan = new Scanner(System.in);*/
		System.out.println("김근형 수정 사항");
		ctx = new GenericXmlApplicationContext("classpath:/com/hb/main/conf1.xml"/*,"classpath:/com/hb/main/conf2.xml"*/);
		System.out.println("Git 변경 관련 실험 로그.");
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
			}else if(command.startsWith("list")){
				processListCommand(command.split(" "));
				continue;
			}else if(command.startsWith("info ")){
				processInfoCommand(command.split(" "));
				continue;
			}else if(command.startsWith("version")){
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}
	private static void processVersionCommand() {
		VersionPrinter vp = ctx.getBean("versionPrinter",VersionPrinter.class);
		vp.print();
	}
	private static void processInfoCommand(String[] arg) {
		MemberInfoPrinter mip = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		mip.printMemberInfo(arg[1]);
		
	}
	private static void processListCommand(String[] arg) {
		MemberListPrinter mlp = ctx.getBean("listPrinter",MemberListPrinter.class);
		mlp.printAll();
		
	}
	private static void processNewCommand(String[] arg){
		if(arg.length != 5){
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("regSvc",MemberRegisterService.class);
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
		ChangePasswordService pwdSvc = ctx.getBean("pwdSvc",ChangePasswordService.class);
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
