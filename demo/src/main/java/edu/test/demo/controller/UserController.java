package edu.test.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.test.demo.service.UserService;
import edu.test.demo.vo.UserVO;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public List test() {
		UserVO user1 = userService.selectUser(1);
		UserVO user2 = userService.selectUser(2);
		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(user1);
		userList.add(user2);
		return userList;
	}
}
