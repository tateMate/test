package edu.test.demo.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.test.demo.service.UserService;
import edu.test.demo.vo.UserVO;

@RestController
public class TestController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("/user")
	public List<UserVO> selectUser(@RequestParam(required = false) Integer id) {
		List<UserVO> li=new ArrayList<UserVO>();
		li.add(userService.selectUserByUserId(id));
		if(id==null) return userService.selectUser();
		return li;
	}
	@GetMapping("/test")
	public String test() {
		return "�쁽�옱 �떆媛곸� "+new Date() + "�엯�땲�떎.";
	}
	@PostMapping("/api/users")
	public UserVO users() {
		List<UserVO> userList = userService.selectUser();
		UserVO user = userList.get(0);
		return user;
	}
}
