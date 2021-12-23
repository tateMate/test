package edu.test.demo.controller;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import edu.test.demo.service.CocommentService;
import edu.test.demo.service.CommentService;
import edu.test.demo.service.UserCharacterService;
import edu.test.demo.service.UserService;
import edu.test.demo.vo.CocommentVO;
import edu.test.demo.vo.CommentVO;
import edu.test.demo.vo.UserVO;

@Controller
public class TestController2 {
	@Autowired
	UserService userService;
	@Autowired
	UserCharacterService userCharacterService;
	@Autowired
	CommentService commentService;
	@Autowired
	CocommentService cocommentService;
//test main page
	@GetMapping("/main")
	public String testpage() {
		return "main/testmain";
	}
//user info
	@GetMapping("/userinfo")
	public String userInfoPage(Model model, @RequestParam(required = false) Integer user_id) {
		model.addAttribute("user",userService.selectUserByUserId(user_id));
		model.addAttribute("character",userCharacterService.selectUserCharacterByUserId(user_id));
		model.addAttribute("comment", commentService.selectCommentByCommentIdTo(user_id));
		model.addAttribute("cocomment", cocommentService.selectCocommentByUserId(user_id));
		return "main/userInfo";
	}
//댓글입력
	@PostMapping("/comment")
	public String PostComment(CommentVO commentVO, HttpSession session){
		commentService.insertComment(commentVO);
		return "main/success";
	}
//대댓글입력
	@PostMapping("/cocomment")
	public String PostCocomment(CocommentVO cocommentVO, HttpSession session){
		System.out.println("대댓글 쓴 이(로그인 중인 id):"+cocommentVO.getCocomment_id_from());
		System.out.println("cocomment id:"+cocommentVO.getCocomment_id()+"/cocomment id:"+cocommentVO.getComment_id());
		cocommentService.insertCocomment(cocommentVO);
		return "main/success";
	}
	
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
			return "main/fail";
		}else {
			session.setAttribute("user", user);
			return "main/success";
		}
		
	}
	
	
	
}
