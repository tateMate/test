package edu.test.demo.controller;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import edu.test.demo.service.CocommentService;
import edu.test.demo.service.CommentService;
import edu.test.demo.service.UserCharacterService;
import edu.test.demo.service.UserService;
import edu.test.demo.vo.CocommentVO;
import edu.test.demo.vo.CommentVO;
import edu.test.demo.vo.UserCharacterVO;
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
	public String testpage(Model model, HttpSession session) {
		if(session.getAttribute("user")!=null) {
			model.addAttribute("rcmd",userCharacterService.sameCharacter((UserCharacterVO)(session.getAttribute("userCharacter"))) );
		}
		//model.addAttribute("rcmd",userService.selectRcmdUserByUserId(((UserVO)(session.getAttribute("user"))).getUser_id()));
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

//modify user info
	@GetMapping("/userinfo/modify")
	public String modifyUserInfoPage(Model model,HttpSession session, @RequestParam Integer user_id) {
		//if (login user != targeted user)can't modify information
		try {
			int session_user_id = ((UserVO) session.getAttribute("user")).getUser_id();
			if (session_user_id == user_id)
				return "main/userModify";
			else {
				model.addAttribute("msg", "Unauthorized User!");
				return "main/fail";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e);
			System.out.println(e);
			return "main/fail";
		}
	}
	@PostMapping("/userinfo/modify")
	public String modifyUserInfo(Model model, UserVO userVO, UserCharacterVO userCharacterVO, HttpSession session, HttpServletRequest request, @RequestParam(value="file") MultipartFile file) {
		try {
			userService.modifyUser(userVO,userCharacterVO,session, request, file);
			return "redirect:/userinfo?user_id="+((UserVO)session.getAttribute("user")).getUser_id();
		}catch (Exception e) {
			model.addAttribute("msg",e);
			System.out.println(e);
			return "main/fail";
		}
	}
	
//댓글입력
	@PostMapping("/comment")
	public String PostComment(Model model, CommentVO commentVO){
		try {
			commentService.insertComment(commentVO);
			return "redirect:/userinfo?user_id="+commentVO.getComment_id_to();
		}catch (Exception e) {
			model.addAttribute("msg",e);
			System.out.println(e);
			return "main/fail";
		}
	}
	//댓글 삭제(해당 댓글의 status를 2로 바꿈)
	@PostMapping("/delco")
	public String deleteComment(Model model, HttpSession session, int comment_id) {
		try {
			//if (login user != commented user) can't delete.
			int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();
			int user_id = commentService.selectCommentByCommentId(comment_id).getComment_id_from();
			if(session_user_id==user_id) {
				commentService.deleteComment(comment_id);
				return "main/success";
			}else {
				System.out.println("YOU. ARE. NOT. THAT. USER!");
				model.addAttribute("msg","Unauthorized User");
				return "main/fail";
			}
		} catch (Exception e) {
			model.addAttribute("msg",e);
			System.out.println(e);
			return "main/fail";
		}
	}
	//댓글 수정(해당 댓글의 status를 1로 바꾸고 log를 새로 생성)
	@PostMapping("/modico")
	public String modifyComment(Model model,HttpSession session, int comment_id, String comment_contents) {
		try {
			//if (login user != commented user) can't modify.
			int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();					//세션 유저 id
			int user_id = commentService.selectCommentByCommentId(comment_id).getComment_id_from();		//댓글을 쓴 유저 id
			if(session_user_id==user_id) {
				commentService.modifyComment(comment_id, comment_contents);
				return "main/success";
			}else {
				System.out.println("YOU. ARE. NOT. THAT. USER!");
				model.addAttribute("msg","Unauthorized User");
				return "main/fail";
			}
		} catch (Exception e) {
			model.addAttribute("msg",e);
			System.out.println(e);
			return "main/fail";
		}
	}
	
	
//대댓글입력
	@PostMapping("/cocomment")
	public String PostCocomment(CocommentVO cocommentVO){
		System.out.println("대댓글 쓴 이(로그인 중인 id):"+cocommentVO.getCocomment_id_from());
		System.out.println("cocomment id:"+cocommentVO.getCocomment_id()+"/cocomment id:"+cocommentVO.getComment_id());
		cocommentService.insertCocomment(cocommentVO);
		return "main/success";
	}
	//대댓글 삭제(해당 대댓글의 status를 2로 바꿈)
	@PostMapping("/delcoco")
	public String deleteCocomment(Model model, HttpSession session, int cocomment_id) {
		try {
			//if (login user != cocommented user) can't delete.
			int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();
			int user_id = cocommentService.selectCocommentByCocommentId(cocomment_id).getCocomment_id_from();
			if(session_user_id==user_id) {
				cocommentService.deleteCocomment(cocomment_id);
				return "main/success";
			}else {
				System.out.println("YOU. ARE. NOT. THAT. USER!");
				model.addAttribute("msg","Unauthorized User");
				return "main/fail";
			}
		} catch (Exception e) {
			model.addAttribute("msg",e);
			System.out.println(e);
			return "main/fail";
		}
	}
	//대댓글 수정
	@PostMapping("/modicoco")
	public String modifyCocomment(Model model,HttpSession session, int cocomment_id, String cocomment_contents) {
		try {
			// if (login user != cocommented user) can't modify.
			int session_user_id = ((UserVO)session.getAttribute("user")).getUser_id();					//세션 유저 id
			int user_id = cocommentService.selectCocommentByCocommentId(cocomment_id).getCocomment_id_from();		//대댓글을 쓴 유저 id
			if(session_user_id==user_id) {
				cocommentService.modifyComment(cocomment_id, cocomment_contents);
				return "main/success";
			}else {
				System.out.println("YOU. ARE. NOT. THAT. USER!");
				model.addAttribute("msg","Unauthorized User");
				return "main/fail";
			}
		} catch (Exception e) {
			model.addAttribute("msg",e);
			System.out.println(e);
			return "main/fail";
		}
	}
	
//회원가입
	@GetMapping("/join")
	public String UserInsertGet() {
		return "main/join";
	}

	@PostMapping("/join")
	public String UserInsertPost(String hiddeninput,Model model, UserVO vo, UserCharacterVO characterVO, HttpServletRequest request, @RequestParam(value="file") MultipartFile file) throws IllegalStateException, IOException {
		try {
			if(hiddeninput.equals("n")) return "redirect:/join";
			userService.insertUser(vo, request, file);
			userCharacterService.insertUserCharacter(characterVO);
			return "redirect:/login";
		}catch (Exception e) {
			model.addAttribute("msg",e);
			return "main/fail";
		}
	}
//회원가입 시 email 중복 체크
	@PostMapping("/emailChk")
	@ResponseBody
	public boolean UserEmailCHK(String user_email,HttpServletRequest request, HttpServletResponse res) {
//		request.setAttribute("bol", userService.emailCheck(user_email));
//		request.setAttribute("test", "testtest");
//		System.out.println(request.getAttribute("bol"));
//		System.out.println(userService.emailCheck(user_email));
		return userService.emailCheck(user_email);
	}

//로그인
	@GetMapping("/login")
	public String loginGet() {
		return "main/login";
	}
	
	@PostMapping("/login")
	public String loginPost(Model model, String user_email, String user_pw, HttpSession session) {
		UserVO user = userService.loginCheck(user_email, user_pw); 
		if (user == null) {
			model.addAttribute("msg","ID와 비밀번호를 확인해주세요");
			return "main/fail";
		}else {
			session.setAttribute("user", user);
			session.setAttribute("userCharacter", userCharacterService.selectUserCharacterByUserId(user.getUser_id()));
			return "redirect:/userinfo?user_id="+user.getUser_id();
		}
		
	}
	
	
	
}
