package edu.test.demo.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.test.demo.service.UserService;
import edu.test.demo.vo.UserVO;

@RestController
public class TestController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("/user")
	public List selectUser() {
		return userService.selectUser();
	}
	@GetMapping("/test")
	public String test() {
		return "현재 시각은 "+new Date() + "입니다.";
	}
	@PostMapping("/api/users")
	public UserVO users() {
		List<UserVO> userList = userService.selectUser();
		UserVO user = userList.get(0);
		return user;
	}
}
