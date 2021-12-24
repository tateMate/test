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
<<<<<<< HEAD
=======

//modify user info
	@GetMapping("/userinfo/modify")
	public String modifyUserInfoPage(HttpSession session, @RequestParam Integer user_id) {
		//if you are not the user, you can't modify information
		int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();
		if(session_user_id == user_id)
			return "main/userModify";
		else
			return "main/fail";
	}
	@PostMapping("/userinfo/modify")
	public String modifyUserInfo(UserVO userVO, UserCharacterVO userCharacterVO, HttpSession session, HttpServletRequest request, @RequestParam(value="file") MultipartFile file) {
		try {
			userService.modifyUser(userVO,userCharacterVO,session, request, file);
			return "main/success";
		}catch (Exception e) {
			System.out.println(e);
			return "main/fail";
		}
	}
	
>>>>>>> 479869e... 211224 session userCharacter, modify file check, comment/cocomment user check
//댓글입력
	@PostMapping("/comment")
	public String PostComment(CommentVO commentVO, HttpSession session){
		commentService.insertComment(commentVO);
		return "main/success";
	}
//댓글 삭제(해당 댓글의 status를 2로 바꿈)
	@PostMapping("/delco")
<<<<<<< HEAD
	public String deleteComment(int comment_id) {
		commentService.deleteComment(comment_id);
		return "main/success";
=======
	public String deleteComment(HttpSession session, int comment_id) {
		//	I'm so sorry but I love you. Doing test is so menndoukusai. 
		//	de I don't do test this yet.
		try {
			//if you are not the user who commented this comment, you can't delete
			int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();
			int user_id = commentService.selectCommentByCommentId(comment_id).getComment_id_from();
			if(session_user_id==user_id) {
				commentService.deleteComment(comment_id);
				return "main/success";
			}else {
				System.out.println("YOU. ARE. NOT. THE. USER!");
				return "main/fail";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "main/fail";
		}
>>>>>>> 479869e... 211224 session userCharacter, modify file check, comment/cocomment user check
	}
	
//대댓글입력
	@PostMapping("/cocomment")
	public String PostCocomment(CocommentVO cocommentVO, HttpSession session){
		System.out.println("대댓글 쓴 이(로그인 중인 id):"+cocommentVO.getCocomment_id_from());
		System.out.println("cocomment id:"+cocommentVO.getCocomment_id()+"/cocomment id:"+cocommentVO.getComment_id());
		cocommentService.insertCocomment(cocommentVO);
		return "main/success";
	}
//대댓글 삭제(해당 대댓글의 status를 2로 바꿈)
	@PostMapping("/delcoco")
<<<<<<< HEAD
	public String deleteCocomment(int cocomment_id) {
		cocommentService.deleteCocomment(cocomment_id);
		return "main/success";
=======
	public String deleteCocomment(HttpSession session, int cocomment_id) {
			//	I'm so sorry but I love you. Doing test is so menndoukusai. 
			//	de, I don't do test this yet.
		try {
			//if you are not the user who commented this comment, you can't delete
			int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();
			int user_id = cocommentService.selectCocommentByCocommentId(cocomment_id).getCocomment_id_from();
			if(session_user_id==user_id) {
				cocommentService.deleteCocomment(cocomment_id);
				return "main/success";
			}else {
				System.out.println("YOU. ARE. NOT. THE. USER!");
				return "main/fail";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "main/fail";
		}
>>>>>>> 479869e... 211224 session userCharacter, modify file check, comment/cocomment user check
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
		if (user == null) {
			return "main/fail";
		}else {
			session.setAttribute("user", user);
			session.setAttribute("userCharacter", userCharacterService.selectUserCharacterByUserId(user.getUser_id()));
			return "main/success";
		}
		
	}
	
	
	
}
