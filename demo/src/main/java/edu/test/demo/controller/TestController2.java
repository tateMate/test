package edu.test.demo.controller;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import edu.test.demo.service.UserService;
import edu.test.demo.vo.UserVO;

@Controller
public class TestController2 {
	@Autowired
	UserService userService;
	
//회원가입
	@GetMapping("/join")
	public String UserInsertGet() {
		return "main/join";
	}

	@PostMapping("/join")
	public String UserInsertPost(UserVO vo, HttpServletRequest request, @RequestParam(value="file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getContentType());
		userService.insertUser(vo, request, file);
		return "main/success";
	}

//로그인
	@GetMapping("/login")
	public String loginGet() {
		return "main/login";
	}
	
	@PostMapping("/login")
	public String loginPost(String user_email, String user_pw, HttpSession session) {
		UserVO user = userService.loginCheck(user_email, user_pw); 
		System.out.println(user_email +" / "+ user_pw);
		if (user == null) {
			System.out.println("로그인실패");
			return "main/fail";
		}else {
			session.setAttribute("user", user);
			System.out.println("로그인 성공");
			return "main/success";
		}
		
	}
	
	
	
}
